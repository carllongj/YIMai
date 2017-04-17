package com.carl.yimai.service.impl;

import cn.carl.cache.redis.RedisCache;
import com.alibaba.fastjson.JSON;
import com.carl.yimai.po.YmItem;
import com.carl.yimai.po.YmOrder;
import com.carl.yimai.pojo.BuyInfo;
import com.carl.yimai.service.CartService;
import com.carl.yimai.service.ItemService;
import com.carl.yimai.service.OrderService;
import com.carl.yimai.service.WalletService;
import com.carl.yimai.web.utils.Result;
import com.carl.yimai.web.utils.Utils;
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

    @Resource(name = "walletService")
    private WalletService walletService;

    @Value("${YIMAI_BASE_PAY_URL}")
    private String YIMAI_BASE_PAY_URL;

    /** 保存任务调度的类 */
    private Map<String,Timer> timerMap = new ConcurrentHashMap<String,Timer>();

    /**
     * 定义在购物时需要保证线程安全
     */
    private Lock lock = new ReentrantLock();

    /**
     * 用户进行拍下商品
     * @param buyerId
     * @param itemId
     * @return
     */
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
        final String orderId = String.valueOf(Utils.getOrderId());

        try {
            //获取校验结果中的正确的信息
            YmItem ymItem = (YmItem) result.getData();
            String ownerId = ymItem.getUid();
            String image = ymItem.getImage();
            ymItem.setStatus(1);
            //更新商品的状态
            itemService.updateItemStatus(ymItem);

            String key = REDIS_BUY_ITEM_KEY + ":buy:" + buyerId;
            String value = redisCache.get(key);
            if (!StringUtils.hasText(value)) {
                //保存订单id到redis中
                redisCache.hset(REDIS_ORDER_ID_HASH_KEY,orderId,key);
                BuyInfo buyInfo = new BuyInfo(buyerId, itemId,ownerId,image,orderId);
                buyInfo.setTitle(ymItem.getTitle());
                buyInfo.setPrice(ymItem.getPrice());
                redisCache.set(key, JSON.toJSONString(buyInfo));
                //设置订单支付的处理时间
                redisCache.expire(key, REDIS_BUY_ITEM_TIME_EXPIRE);
                Date expireDate = new DateTime().plusSeconds(REDIS_BUY_ITEM_TIME_EXPIRE).toDate();

                String price = String.valueOf(ymItem.getPrice());

                orderService.createOrder(buyInfo,price);
                //设置任务调度,过期,直接删除redis中保存的数据
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
            }else{
                return Result.error("您有订单还在处理中");
            }
        }catch (Exception e) {
            //出现异常时删除redis中的信息
            redisCache.hdel(REDIS_ORDER_ID_HASH_KEY,orderId);
            orderService.deleteOrder(Long.parseLong(orderId));

            return Result.error("服务器出现故障,请稍候重试");
        } finally {
            lock.unlock();
        }
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

            orderService.deleteOrder(Long.parseLong(orderId));

            ymItem.setStatus(0);
            itemService.updateItemStatus(ymItem);
        }
    }

    @Override
    public Result checkInfo(String userId, String itemId) {
        Result result = itemService.findItem(itemId);
        if (!result.isStatus()) {
            return result;
        }

        YmItem ymItem = (YmItem) result.getData();

        if (0 != ymItem.getStatus()){
            return Result.error("当前商品已经被拍下,请浏览其他的商品");
        }

        if (ymItem.getUid().equals(userId)){
            return Result.error("此商品为您要出售的商品,禁止购买");
        }

        return result.ok();
    }

    @Override
    public Result pay(String userId,Long orderId) {

        String key = REDIS_BUY_ITEM_KEY + ":buy:" + userId;

        //先从缓存中获取当前的交易是否存在,没有获取到表示不存在
        String value = redisCache.get(key);

        if (!StringUtils.hasText(value)){
             return Result.error("当前的交易已经取消,你可以尝试重新拍下此商品再进行支付");
        }

        Result result = orderService.getOrder(userId, orderId);

        if (!result.isStatus()){
            return result;
        }

        YmOrder order = (YmOrder) result.getData();

        if (!userId.equals(order.getBuyerid())){
            return Result.error("当前的信息异常,请稍候重试");
        }

        Result back = this.successBack(String.valueOf(orderId));
        return back;
    }

    /**
     * 用户支付成功后需要执行的回调方法
     * @param orderId
     * @return
     */
    private Result successBack(String orderId) {

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

            //进行钱包转账处理
            Result result = walletService.payment(buyInfo.getUserId(), buyInfo.getOwnerId(), buyInfo.getPrice());

            if (!result.isStatus()){
                return result;
            }
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
    public String getPayUrl() {
        return YIMAI_BASE_PAY_URL;
    }
}
