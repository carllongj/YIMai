package com.carl.yimai.web.controller;

import com.carl.yimai.service.CartService;
import com.carl.yimai.service.WalletService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

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

    @Resource(name = "walletService")
    private WalletService walletService;

    /**
     * 用户拍下商品
     * 正常测试结果 √ 如果超时,会删除redis中的所有的无效的数据
     * @param request
     * @param itemId
     * @return
     */
    @RequestMapping("/buyItem.action")
    @ResponseBody
    public Result buyItem(HttpServletRequest request, String itemId){
        String buyerId = (String) request.getAttribute("userId");
        //进行购买商品
        Result result = cartService.buyItem(buyerId, itemId);
        return result;
    }

    @RequestMapping("/check.action")
    @ResponseBody
    public Result checkInfo(HttpServletRequest request,String itemId){
        if(!StringUtils.hasText(itemId)){
            return Result.error("参数错误");
        }

        String userId = (String) request.getAttribute("userId");

        Result result = cartService.checkInfo(userId, itemId);

        return result;
    }
    /**
     * 用户准备支付前的工作
     * 正常测试结果 √
     * @param request
     * @return
     */
    @RequestMapping("/payItem")
    @ResponseBody
    public Result payItem(HttpServletRequest request,String orderId) throws Exception {

        if (!StringUtils.hasText(orderId)){
            return Result.error("不合法参数");
        }

        Long oid;
        try{
            oid = Long.parseLong(orderId);
        }catch (Exception e){
            return Result.error("不合法参数");
        }

        String userId = (String) request.getAttribute("userId");
        //测试支付的功能
        Result result = cartService.pay(userId,oid);

        return result;
    }

    @RequestMapping("/checkRemain")
    @ResponseBody
    public Result check(HttpServletRequest request,String money){
        String userId = (String) request.getAttribute("userId");
        BigDecimal bigDecimal = new BigDecimal(money);
        Integer price = bigDecimal.multiply(new BigDecimal("100")).intValue();

        Result result = walletService.checkRemain(userId, price);
        return result;
    }

}
