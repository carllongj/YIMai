package com.carl.yimai.service;

import com.carl.yimai.web.utils.Result;

/**
 * 本项目没有购物车的功能,但是需要模拟购物车的效果
 * 因为涉及到过时会取消交易,所以需要使用任务调度
 * 两种解决方案:
 * 1.使用redis来保存数据从而完成任务调度和创建订单
 * 2.直接创建订单信息,然后使用redis来进行控制时间,
 *   完成任务调度,过时未支付取消交易修改商品的状态信息
 * <p>Title: com.carl.yimai.service CartService</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/29 17:53
 * @Version 1.0
 */
public interface CartService {

    /**
     * 用户拍下商品
     * @param itemId
     * @param buyerId
     * @return
     */
    Result buyItem(String buyerId,String itemId);

    /**
     * 取消用户的购买信息,恢复商品让其他用户购买的权限
     * @param buyerId 购买者的id
     */
    void cancel(String buyerId,String itemId);

    /**
     * 用户在付款之前需要确认当前的商品的状态
     * @param userId
     */
    Result pay(String userId);

    /**
     * 用户支付成功后被第三方支付调用的方法
     * @param orderId
     * @param price
     * @return
     */
    Result successBack(String orderId,String price);

    /**
     * 用户如果没有支付成功需要调用的方法
     * @param orderId
     * @return
     */
    Result failBack(String orderId);
}
