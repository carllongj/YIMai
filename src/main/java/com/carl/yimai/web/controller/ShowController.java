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

    @RequestMapping("/index.action")
    public String showIndex(){
        //可以查询数据库来增加巨幕的广告
        return "index";
    }

    @RequestMapping("/page/login.action")
    public String showLogin(HttpServletRequest request,String redirect){
        String callback = null;
        if(StringUtils.hasText(redirect)){
            callback = redirect;
        }
        request.setAttribute("redirect",callback);
        return "login";
    }
}
