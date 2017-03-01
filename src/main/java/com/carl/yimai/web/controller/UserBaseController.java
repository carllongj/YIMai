package com.carl.yimai.web.controller;

import cn.carl.web.cookie.CookieTools;
import com.carl.yimai.po.YmUser;
import com.carl.yimai.service.UserService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户基本服务的controller
 * 当前的controller不被拦截器进行拦截
 * <p>Title: com.carl.yimai.web.controller UserController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/24 14:10
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserBaseController {

    @Resource(name = "userService")
    private UserService userService;

    /**
     * 检测当前的邮箱是否已被注册
     * 正常测试结果  √
     * @param username
     * @return
     */
    @RequestMapping("/name/{username}")
    @ResponseBody
    public String checkUsername(@PathVariable String username){
            Result result = userService.checkUsername(username);
        if (result.isStatus()) {
            return "{\"valid\":\"true\"}";
        }else
            return "{\"valid\":\"false\"}";
    }

    /**
     * 检测当前的邮箱是否已被注册
     * 正常测试结果   √
     * @param email
     * @return
     */
    @RequestMapping("/email/{email}")
    @ResponseBody
    public Result checkEmail(@PathVariable String email) {
            Result result = userService.checkEmail(email);
            return result;
    }

    /**
     * 提供用户注册的功能
     * 正常测试结果  √
     * @param ymUser
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Result register(YmUser ymUser){
            Result result = userService.register(ymUser);
            return result;
    }

    /**
     * 用户登录系统的功能
     * 正常测试结果 √
     * @param ymUser
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login.action",method = RequestMethod.POST)
    @ResponseBody
    public Result login(YmUser ymUser, HttpServletRequest request, HttpServletResponse response){
        CookieTools.setRequestAndResponse(request,response);
        Result result = userService.login(ymUser.getUsername(), ymUser.getPasswd());
        CookieTools.remove();
        return result;
    }

    /**
     * 用户激活注册账户的邮箱的功能
     * 正常测试结果 √
     * @param key
     * @param activeCode
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/code/{key}/{activeCode}")
    public String active(@PathVariable String key,
                         @PathVariable String activeCode, Model model) throws Exception {
            Result result = userService.activated(key, activeCode);
        if (result.isStatus()) {
            return "redirect:/page/login.action";
        }else {
            model.addAttribute("message","校验失败,请点击重新获取激活邮件的信息");
            return "/error/error";
        }
    }

    /**
     * 用户重新获取邮箱的激活码的功能
     * 正常测试结果 √
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/email/resend.action")
    @ResponseBody
    public Result resendEmail(String username,String password){

        Result result = userService.resendEmail(username, password);

        return result;
    }
}
