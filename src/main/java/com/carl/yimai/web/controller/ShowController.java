package com.carl.yimai.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面的展示controller
 * <p>Title: com.carl.yimai.web.controller ShowController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/24 18:16
 * @Version 1.0
 */
@Controller
public class ShowController {

    /**
     * 展示系统的首页
     * 测试结果 √
     * @return
     */
    @RequestMapping("/index.action")
    public String showIndex(){
        //可以查询数据库来增加巨幕的广告
        return "index";
    }

    /**
     * 展示系统的登录的页面
     * 测试结果 √
     * @param request
     * @param redirect
     * @return
     */
    @RequestMapping("/page/signin.action")
    public String showLogin(HttpServletRequest request,String redirect){
        String callback = null;
        if(StringUtils.hasText(redirect)){
            callback = redirect;
        }
        request.setAttribute("redirect",callback);
        return "signin";
    }

    /**
     * 展示系统的注册页面
     * 测试结果 √
     * @return
     */
    @RequestMapping("/page/signup.action")
    public String showRegister(){
        return "signup";
    }
}
