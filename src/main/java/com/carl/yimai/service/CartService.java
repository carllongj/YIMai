package com.carl.yimai.service;

import com.carl.yimai.web.utils.Result;

/**
 * 本项目没有购物车的功能,但是需要使用redis缓存来模拟购物车的效果
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
}
