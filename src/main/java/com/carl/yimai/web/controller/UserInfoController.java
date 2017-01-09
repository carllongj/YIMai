package com.carl.yimai.web.controller;

import com.carl.yimai.pojo.UserInfo;
import com.carl.yimai.service.UserService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Controller;
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
}
