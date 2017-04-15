package com.carl.yimai.service;

import com.carl.yimai.po.YmOrder;
import com.carl.yimai.pojo.BuyInfo;
import com.carl.yimai.pojo.OrderInfo;
import com.carl.yimai.web.utils.Result;
import cn.carl.page.PageResult;

/**
 * 订单Service
 * <p>Title: com.carl.yimai.service</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/30 15:06
 * @Version 1.0
 */
public interface OrderService {

    /**
     * 创建订单
     * @param buyInfo
     * @param price
     * @return
     */
    Result createOrder(BuyInfo buyInfo,String price);

    /**
     * 用户如果需要添加对商品的留言信息
     * @return
     */
    Result addComment(String orderId,String comment);

    /**
     * 用户查看当前的所有的已购买商品的订单
     * @param buyerId
     * @param page
     * @param type
     * @return
     */
    PageResult<YmOrder> showOrders(String buyerId,Integer page,Integer type);

    /**
     * 管理员可以对订单信息进行修改
     * @param orderInfo
     * @return
     */
    Result updateOrder(OrderInfo orderInfo);

    /**
     * 删除用户未购买或者付款的订单
     * @param orderId
     * @return
     */
    Result deleteOrder(Long orderId);

    /**
     * 用户取消订单信息
     * @param userId
     * @param orderId
     * @return
     */
    Result cancelOrder(String userId,String orderId);
}
