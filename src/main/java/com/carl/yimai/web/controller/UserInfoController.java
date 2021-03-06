package com.carl.yimai.web.controller;

import com.alibaba.fastjson.JSON;
import com.carl.yimai.po.YmUserAddr;
import com.carl.yimai.pojo.UserInfo;
import com.carl.yimai.service.AddressService;
import com.carl.yimai.service.UserService;
import com.carl.yimai.service.WalletService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息的controller,此controller可能会被拦截器进行拦截处理
 * <p>Title: com.carl.yimai.web.controller UserInfoController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/25 21:07
 * @Version 1.0
 */
@Controller
@RequestMapping("/userinfo")
public class UserInfoController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "addressService")
    private AddressService addressService;

    @Resource(name = "walletService")
    private WalletService walletService;

    /** ===============    用户的个人中心     ==================*/
    /**
     * 允许用户修改一些基本信息
     * 正常测试结果 √
     * @param request
     * @param userInfo
     * @return
     */
    @RequestMapping("/update.action")
    @ResponseBody
    public Result updateUser(HttpServletRequest request,UserInfo userInfo){
        //获取从登陆系统中获取的用户的id
        String userId = (String) request.getAttribute("userId");

        userInfo.setId(userId);

        userService.updateUserInfo(userInfo);

        return Result.ok();
    }

    /**
     * 用户获取自己的信息
     * @return
     */
    @RequestMapping("/user.action")
    public String getUserInfo(HttpServletRequest req,Model m){

        //获取用户的id信息
        String userId = (String) req.getAttribute("userId");
        Result result = userService.getUserInfoById(userId);
        m.addAttribute("info", JSON.toJSONString(result.getData()));
        return "user_userinfo";
    }

    /** ===============    用户的个人中心     ==================*/

    /** ===============    用户的地址选择        ==================*/

    @RequestMapping("/myaddr.action")
    public String getMyAddresses(){
        return "user_myaddr";
    }

    @RequestMapping("/show/myaddresses.action")
    @ResponseBody
    public Result showMyAddresses(HttpServletRequest request){
        String userId = (String) request.getAttribute("userId");
        Result result = addressService.getMyAddresses(userId);
        return result;
    }

    @RequestMapping("/add/addr")
    @ResponseBody
    public Result addAddress(YmUserAddr addr,HttpServletRequest request){
        String userId = (String) request.getAttribute("userId");
        Result result = addressService.addAddress(userId, addr);
        return result;
    }

    @RequestMapping("/del/addr")
    @ResponseBody
    public Result deleteAddress(HttpServletRequest request,String id){

        String uid = (String) request.getAttribute("userId");
        Result result = addressService.deleteAddress(uid, id);

        return result;
    }

    @RequestMapping("/def/addr.action")
    @ResponseBody
    public Result setDefaultAddress(HttpServletRequest request,String id){
        String uid = (String) request.getAttribute("userId");
        Result result = addressService.setDefault(uid, id);
        return result;
    }

    /** ===============    用户的地址选择        ==================*/

    /** ===============    用户的订单页面     ==================*/

    @RequestMapping("/order/myorders")
    public String showMyOrders(){
        return "user_myorders";
    }

    /** ===============   用户待售商品页面   ================= */
    @RequestMapping("/selling")
    public String showSelling(){
        return "user_selling";
    }


    /** ===============    用户的所有卖出页面     ==================*/
    @RequestMapping("/allsell.action")
    public String showAllSell(){
        return "user_allsell";
    }

    /** ===============    用户的所有买入商品页面     ==================*/
    @RequestMapping("/allbuy.action")
    public String showAllBuy(){
        return "user_allbuy";
    }

    /** ==================   用户的账单的请求控制   =====================*/
    @RequestMapping("/account")
    public String showAccout(){
        return "user_accout";
    }

    /** ===============    用户钱包的控制    ==================*/

    @RequestMapping("/wallet/check")
    @ResponseBody
    public Result checkWallet(HttpServletRequest request){
        String userId = (String) request.getAttribute("userId");
        Result result = walletService.checkStatus(userId);
        return result;
    }
}
