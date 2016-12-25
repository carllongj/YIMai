package com.carl.yimai.web.controller;

import cn.carl.web.cookie.CookieTools;
import com.carl.yimai.po.YmUser;
import com.carl.yimai.service.UserService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/name/{username}")
    @ResponseBody
    public Result checkUsername(@PathVariable String username){
            Result result = userService.checkUsername(username);
            return result;
    }

    @RequestMapping("/email/{email}")
    @ResponseBody
    public Result checkEmail(@PathVariable String email) {
            Result result = userService.checkEmail(email);
            return result;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Result register(YmUser ymUser){
            Result result = userService.register(ymUser);
            return result;
    }

    @RequestMapping(value = "/login.action",method = RequestMethod.POST)
    @ResponseBody
    public Result login(YmUser ymUser, HttpServletRequest request, HttpServletResponse response){
        CookieTools.setRequestAndResponse(request,response);
        Result result = userService.login(ymUser.getUsername(), ymUser.getPasswd());
        CookieTools.remove();
        return result;
    }

    @RequestMapping("/code/{userId}/{activeCode}")
    public Result active(@PathVariable String userId,
                         @PathVariable String activeCode){
            Result result = userService.activated(userId, activeCode);
            return result;
    }
}