package com.carl.yimai.service;

import com.carl.yimai.pojo.BuyInfo;
import com.carl.yimai.web.utils.Result;

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
}
