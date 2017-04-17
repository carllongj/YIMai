package com.carl.yimai.service.impl;

import com.carl.yimai.mapper.YmOrderMapper;
import com.carl.yimai.po.YmOrder;
import com.carl.yimai.po.YmOrderExample;
import com.carl.yimai.pojo.BuyInfo;
import com.carl.yimai.pojo.OrderInfo;
import com.carl.yimai.service.CartService;
import com.carl.yimai.service.OrderService;
import com.carl.yimai.web.utils.Result;
import cn.carl.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
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

    @Resource(name = "cartService")
    private CartService cartService;

    @Value("${USER_ORDER_PAGE_ROWS}")
    private Integer USER_ORDER_PAGE_ROWS;

    @Override
    public Result createOrder(BuyInfo buyInfo, String price) {
        YmOrder order = new YmOrder();

        //补全订单的信息
        order.setId(Long.valueOf(buyInfo.getOrderId()));
        order.setCreated(new Date());
        order.setItemid(buyInfo.getItemId());
        order.setTitle(buyInfo.getTitle());
        order.setImage(buyInfo.getImage());
        order.setExpire(new DateTime().plusDays(20).toDate());
        order.setStatus(0);

        //保存价格
        BigDecimal bigDecimal = new BigDecimal(price);
        Integer realPrice = bigDecimal.intValue();
        order.setPrice(realPrice);
        order.setBuyerid(buyInfo.getUserId());

        order.setSellerid(buyInfo.getOwnerId());
        orderMapper.insert(order);

        return Result.ok();
    }

    @Override
    public Result getOrder(String userId, Long orderId) {
        YmOrder order = orderMapper.selectByPrimaryKey(orderId);

        if (null == order){
            return Result.error("没有相关的订单信息");
        }

        if (!order.getBuyerid().equals(userId)){
            return Result.error("没有相关的订单信息");
        }

        return Result.ok(order);
    }

    /**
     * 分页查询指定的订单
     * @param buyerId
     * @param page
     * @param type
     * @return
     */
    @Override
    public PageResult<YmOrder> showOrders(String buyerId,Integer page,Integer type){
        PageHelper.startPage(page ,USER_ORDER_PAGE_ROWS);
        YmOrderExample example = new YmOrderExample();
        YmOrderExample.Criteria criteria = example.createCriteria().andBuyeridEqualTo(buyerId);

        if (-1 != type){
            criteria.andStatusEqualTo(type);
        }
        //查询当前用户的所有的订单信息
        List<YmOrder> ymOrders =
                orderMapper.selectByExample(example);
        PageInfo<YmOrder> pageInfo = new PageInfo<YmOrder>(ymOrders);
        PageResult<YmOrder> result = PageResult.newInstance(pageInfo.getTotal(), USER_ORDER_PAGE_ROWS, ymOrders);
        return result;
    }

    @Override
    public Result updateOrder(OrderInfo orderInfo) {
        YmOrder order = new YmOrder();
        BeanUtils.copyProperties(orderInfo,order);
        orderMapper.updateByPrimaryKeySelective(order);
        return Result.ok();
    }

    /**
     * 删除订单信息
     * @param orderId
     * @return
     */
    @Override
    public Result deleteOrder(Long orderId) {
        orderMapper.deleteByPrimaryKey(orderId);
        return Result.ok();
    }

    @Override
    public Result cancelOrder(String userId, String orderId) {
        long id = Long.parseLong(orderId);
        YmOrder order = orderMapper.selectByPrimaryKey(id);

        if (null == order || !order.getBuyerid().equals(userId)) {
            return Result.error("没有相关的订单信息");
        }

        if (0 == order.getStatus()) {
            cartService.cancel(userId, order.getItemid(), String.valueOf(order.getId()));
            return Result.ok();
        }else if (1 == order.getStatus()){
            return Result.error("商品处于待收货状态,不能取消");
        }else {
            return this.deleteOrder(order.getId());
        }
    }
}