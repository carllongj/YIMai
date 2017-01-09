package com.carl.yimai.service.impl;

import cn.carl.cache.redis.RedisCache;
import cn.carl.string.StringTools;
import com.alibaba.fastjson.JSON;
import com.carl.yimai.po.YmItem;
import com.carl.yimai.pojo.BuyInfo;
import com.carl.yimai.service.CartService;
import com.carl.yimai.service.ItemService;
import com.carl.yimai.service.OrderService;
import com.carl.yimai.web.utils.Result;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 对购物车使用redis来实现,带有时间限制,这种方式主要以操作redis为主
 * <p>Title: com.carl.yimai.service.impl CartServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/29 17:55
 * @Version 1.0
 */
@Service("cartService")
public class CartServiceImpl implements CartService {

    @Resource(name = "redisCache")
    private RedisCache redisCache;

    @Value("${REDIS_BUY_ITEM_KEY}")
    private String REDIS_BUY_ITEM_KEY;

    @Value("${REDIS_BUY_ITEM_TIME_EXPIRE}")
    private Integer REDIS_BUY_ITEM_TIME_EXPIRE;

    @Value("${REDIS_ORDER_ID_HASH_KEY}")
    private String REDIS_ORDER_ID_HASH_KEY;

    @Resource(name = "itemService")
    private ItemService itemService;

    @Resource(name = "orderService")
    private OrderService orderService;

    @Value("${YIMAI_BASE_PAY_URL}")
    private String YIMAI_BASE_PAY_URL;

    /** 保存任务调度的类 */
    private Map<String,Timer> timerMap = new ConcurrentHashMap<String,Timer>();

    /**
     * 定义在购物时需要保证线程安全
     */
    private Lock lock = new ReentrantLock();

    @Override
    public Result buyItem(final String buyerId, final String itemId) {

        Result result = checkItem(itemId);

        if (!result.isStatus()) {
            return result;
        }

        //购买商品时加锁
        lock.lock();

        Result newResult = checkItem(itemId);

        //双重检查当前商品是否可用
        if (!newResult.isStatus()) {
            return newResult;
        }
        //产生保存用户订单的id
        final String orderId = StringTools.uuid();

        try {
            //获取校验结果中的正确的信息
            YmItem ymItem = (YmItem) result.getData();
            String ownerId = ymItem.getUid();
            ymItem.setStatus(1);
            //更新商品的状态
            itemService.updateItemStatus(ymItem);

            String key = REDIS_BUY_ITEM_KEY + ":buy:" + buyerId;
            String value = redisCache.get(key);
            if (!StringUtils.hasText(value)) {
                //保存订单id到redis中
                redisCache.hset(REDIS_ORDER_ID_HASH_KEY,orderId,key);
                BuyInfo buyInfo = new BuyInfo(buyerId, itemId,ownerId);
                redisCache.set(key, JSON.toJSONString(buyInfo));
                //设置订单支付的处理时间
                redisCache.expire(key, REDIS_BUY_ITEM_TIME_EXPIRE);
                Date expireDate = new DateTime().plusSeconds(REDIS_BUY_ITEM_TIME_EXPIRE).toDate();

                //设置任务调度,过期,直接删除redis中保存的数据,
                // 并且重新更新数据库的信息
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        CartServiceImpl.this.cancel(buyerId,itemId,orderId);
                    }
                };
                //设置过期的任务调度控制
                timer.schedule(task,expireDate);
                //保存定时器到当前的对象中
                timerMap.put(key,timer);
                return result.ok(orderId);
            }
        } finally {
            lock.unlock();
        }
        return Result.error("系统错误,请联系管理员处理");
    }

    /**
     * 校验商品是否可用
     * @param itemId
     * @return
     */
    private Result checkItem(String itemId) {
        Result result = itemService.findItem(itemId);
        YmItem ymItem = (YmItem) result.getData();
        if (null == ymItem) {
            return Result.error("没有当前商品的相关信息");
        }

        if (ymItem.getStatus() != 0) {
            return Result.error("当前商品已经被拍下");
        }

        return Result.ok(ymItem);
    }

    /**
     * 过期取消用户的购买信息
     */
    @Override
    public void cancel(String buyerId, String itemId,String orderId) {
        Result result = itemService.findItem(itemId);
        YmItem ymItem = (YmItem) result.getData();

        if (null != ymItem) {
            String key = REDIS_BUY_ITEM_KEY + ":buy:" + buyerId;
            String value = redisCache.get(key);

            if (StringUtils.hasText(value)) {
                redisCache.del(key);
            }
            //删除当前订单信息
            redisCache.hdel(REDIS_ORDER_ID_HASH_KEY,orderId);

            ymItem.setStatus(0);
            itemService.updateItemStatus(ymItem);
        }
    }

    @Override
    public Result pay(String userId) {
        String key = REDIS_BUY_ITEM_KEY + ":buy:" + userId;

        //先从缓存中获取当前的交易是否存在,没有获取到表示不存在
        String value = redisCache.get(key);

        if (!StringUtils.hasText(value)){
             return Result.error("当前的交易已经取消,你可以尝试重新拍下此商品再进行支付");
        }

        return Result.ok();
    }

    @Override
    public Result successBack(String orderId,String price) {

        /*REDIS_BUY_ITEM_KEY:buy:123456789*/
        String value = redisCache.hget(REDIS_ORDER_ID_HASH_KEY, orderId);

        if (StringUtils.hasText(value)){

            //取出任务调度,并且取消任务调度
            Timer timer = timerMap.get(value);
            if (null == timer) {
                return Result.error("当前的订单已经取消,请尝试重新拍下再付款");
            }
            timer.cancel();

            String order = redisCache.get(value);
            BuyInfo buyInfo = JSON.parseObject(order, BuyInfo.class);
            //创建商品的订单信息
            orderService.createOrder(buyInfo,price);
            //更新商品的状态信息
            itemService.updateItemStatus(buyInfo.getItemId(),2);
            //删除相关的保存在redis中的键值对
            redisCache.del(value);
            redisCache.hdel(REDIS_ORDER_ID_HASH_KEY,orderId);
            return Result.ok();
        }
        return Result.error("系统错误,请稍候再试");
    }

    @Override
    public Result failBack(String orderId) {

        String key = redisCache.hget(REDIS_ORDER_ID_HASH_KEY,orderId);

        if (StringUtils.hasText(key)) {
            Long remain = redisCache.ttl(key);

            return Result.ok("你还有" + remain + "时间来完成支付,请尽快完成支付");
        }
        return Result.error("系统错误,请稍后重试");
    }

    @Override
    public String getPayUrl() {
        return YIMAI_BASE_PAY_URL;
    }
}
