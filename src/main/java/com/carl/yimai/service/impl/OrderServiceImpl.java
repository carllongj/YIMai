package com.carl.yimai.service.impl;

import com.carl.yimai.mapper.YmOrderMapper;
import com.carl.yimai.po.YmOrder;
import com.carl.yimai.pojo.BuyInfo;
import com.carl.yimai.service.OrderService;
import com.carl.yimai.web.utils.IDUtils;
import com.carl.yimai.web.utils.Result;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Title: com.carl.yimai.service.impl OrderServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/30 15:07
 * @Version 1.0
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService{

    @Resource(name = "ymOrderMapper")
    private YmOrderMapper orderMapper;

    @Override
    public Result createOrder(BuyInfo buyInfo, String price) {
        YmOrder order = new YmOrder();

        //补全订单的信息
        order.setId(IDUtils.getOrderId());
        order.setCreated(new Date());
        order.setExpire(new DateTime().plusDays(20).toDate());
        order.setStatus(1);

        //保存价格
        BigDecimal bigDecimal = new BigDecimal(price);
        BigDecimal multiply = bigDecimal.multiply(new BigDecimal("100"));
        Integer realPrice = multiply.intValue();
        order.setPrice(realPrice);

        order.setSellerid(buyInfo.getOwnerId());
        orderMapper.insert(order);

        return Result.ok();
    }


}
