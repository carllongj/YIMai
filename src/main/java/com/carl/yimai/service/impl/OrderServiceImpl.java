package com.carl.yimai.service.impl;

import com.carl.yimai.mapper.YmOrderMapper;
import com.carl.yimai.po.YmOrder;
import com.carl.yimai.po.YmOrderExample;
import com.carl.yimai.pojo.BuyInfo;
import com.carl.yimai.pojo.OrderInfo;
import com.carl.yimai.service.OrderService;
import com.carl.yimai.web.utils.Result;
import com.carl.yimai.web.utils.Utils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
        order.setId(Utils.getOrderId());
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

    /**
     * 用户添加留言信息
     * @param orderId
     * @param comment
     * @return
     */
    @Override
    public Result addComment(String orderId, String comment) {
        Integer id = Integer.parseInt(orderId);
        YmOrder order = orderMapper.selectByPrimaryKey(id);
        order.setComment(comment);
        orderMapper.updateByPrimaryKeySelective(order);
        return Result.ok();
    }

    @Override
    public Result showOrders(String buyerId){
        YmOrderExample example = new YmOrderExample();
        YmOrderExample.Criteria criteria = example.createCriteria();
        criteria.andBuyeridEqualTo(buyerId);
        //查询当前用户的所有的
        List<YmOrder> ymOrders =
                orderMapper.selectByExample(example);
        return Result.ok(ymOrders);
    }

    @Override
    public Result updateOrder(OrderInfo orderInfo) {
        YmOrder order = new YmOrder();
        BeanUtils.copyProperties(orderInfo,order);
        orderMapper.updateByPrimaryKeySelective(order);
        return Result.ok();
    }
}