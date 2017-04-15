package com.carl.yimai.web.controller;

import cn.carl.page.PageResult;
import com.carl.yimai.po.YmOrder;
import com.carl.yimai.service.CartService;
import com.carl.yimai.service.OrderService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 订单服务控制器
 * <p>Title: com.carl.yimai.web.controller OrderController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/3/12 20:17
 * @Version 1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource(name = "orderService")
    private OrderService orderService;

    /**
     * 用户查询自己的订单的信息
     * @param request
     * @return
     */
    @RequestMapping("/myorders.action")
    @ResponseBody
    public PageResult<YmOrder> listMyOrders(HttpServletRequest request,
                               @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "-1") Integer type){
        String userId = (String) request.getAttribute("userId");
        PageResult<YmOrder> result = orderService.showOrders(userId,page,type);
        return result;
    }

    @RequestMapping("/del.action")
    @ResponseBody
    public Result deleteOrder(HttpServletRequest request,String id){
        String userId = (String) request.getAttribute("userId");
        Result result = orderService.cancelOrder(userId, id);
        return result;
    }
}
