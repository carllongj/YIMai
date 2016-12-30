package com.carl.yimai.web.controller;

import com.carl.yimai.service.CartService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支付相关的调用controller
 * <p>Title: com.carl.yimai.web.controller CartController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/30 15:35
 * @Version 1.0
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Resource(name = "cartService")
    private CartService cartService;

    /**
     * 用户拍下商品
     * @param request
     * @param itemId
     * @return
     */
    @RequestMapping("/buyItem")
    @ResponseBody
    public Result buyItem(HttpServletRequest request,String itemId){
        String buyerId = (String) request.getAttribute("userId");
        Result result = cartService.buyItem(buyerId, itemId);
        return result;
    }

    /**
     * 用户准备支付前的工作
     * @param request
     * @return
     */
    @RequestMapping("/payItem")
    public void payItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = (String) request.getAttribute("userId");
        Result result = cartService.pay(userId);
        if (result.isStatus()) {
            //支付的调用
            String payUrl = cartService.getPayUrl();
            //去支付
            response.sendRedirect(payUrl);
        }else {
            throw new Exception(result.getMsg());
        }
    }

    @RequestMapping("/back")
    public Result back(boolean status,String orderId,String price){
        //校验支付后返回的结果
        if (status) {
            //如果支付成功需要做的事
            Result result = cartService.successBack(orderId, price);
            return result;
        }else{
            //如果支付失败需要做的事
            Result result = cartService.failBack(orderId);
            return result;
        }
    }
}
