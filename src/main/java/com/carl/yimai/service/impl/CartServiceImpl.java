package com.carl.yimai.service.impl;

import cn.carl.cache.redis.RedisCache;
import com.alibaba.fastjson.JSON;
import com.carl.yimai.mapper.YmItemMapper;
import com.carl.yimai.po.YmItem;
import com.carl.yimai.pojo.BuyInfo;
import com.carl.yimai.service.CartService;
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

    @Resource(name = "ymItemMapper")
    private YmItemMapper itemMapper;

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

        //双重检查当前商品是否可用
        if (!result.isStatus()) {
            return result;
        }

        try {
            //获取校验结果中的正确的信息
            YmItem ymItem = (YmItem) result.getData();
            ymItem.setStatus(1);
            //更新商品的状态
            itemMapper.updateByPrimaryKeySelective(ymItem);

            String key = REDIS_BUY_ITEM_KEY + ":buy:" + buyerId;
            String value = redisCache.get(key);
            if (!StringUtils.hasText(value)) {
                BuyInfo buyInfo = new BuyInfo(buyerId, itemId);
                redisCache.set(key, JSON.toJSONString(buyInfo));
                redisCache.expire(key, REDIS_BUY_ITEM_TIME_EXPIRE);
                Date expireDate = new DateTime().plusSeconds(REDIS_BUY_ITEM_TIME_EXPIRE).toDate();

                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        CartServiceImpl.this.cancel(buyerId,itemId);
                    }
                };
                //设置过期的任务调度控制
                timer.schedule(task,expireDate);
                //保存定时器到当前的对象中
                timerMap.put(key,timer);
            }
        } finally {
            lock.unlock();
        }
        return Result.ok();
    }

    /**
     * 校验商品是否可用
     * @param itemId
     * @return
     */
    private Result checkItem(String itemId) {
        YmItem ymItem = itemMapper.selectByPrimaryKey(itemId);

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
    public void cancel(String buyerId, String itemId) {
        YmItem ymItem = itemMapper.selectByPrimaryKey(itemId);

        if (null != ymItem) {
            String key = REDIS_BUY_ITEM_KEY + ":buy:" + buyerId;
            String value = redisCache.get(key);

            if (StringUtils.hasText(value)) {
                redisCache.del(key);
            }

            ymItem.setStatus(0);
            itemMapper.updateByPrimaryKey(ymItem);
        }
    }

    @Override
    public Result pay(String userId) {
        String key = REDIS_BUY_ITEM_KEY + ":buy:" + userId;

        //先从缓存中获取当前的交易是否存在,没有获取到表示不存在
        String value = redisCache.get(key);

        if (!StringUtils.hasText(value)){
             return Result.error("当前的交易已经取消");
        }
        Timer timer = timerMap.get(key);

        //这里进行付款的调用

        //取消任务调度
        timer.cancel();
        //删除键值对
        timerMap.remove(key);
        return Result.ok();
    }
}
