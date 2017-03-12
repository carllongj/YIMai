package com.carl.yimai.web.controller;

import com.alibaba.fastjson.JSON;
import com.carl.yimai.service.OrderService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String listMyOrders(HttpServletRequest request,Model model){

        String userId = (String) request.getAttribute("userId");
        Result result = orderService.showOrders(userId);
        model.addAttribute("orders", JSON.toJSONString(result));

        return "myorders";
    }
}
