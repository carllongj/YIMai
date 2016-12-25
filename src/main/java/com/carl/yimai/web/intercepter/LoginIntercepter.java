package com.carl.yimai.web.intercepter;

import cn.carl.web.cookie.CookieTools;
import com.carl.yimai.po.YmUser;
import com.carl.yimai.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录拦截器
 * <p>Title: com.carl.yimai.web LoginIntercepter</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/25 14:15
 * @Version 1.0
 */
public class LoginIntercepter implements HandlerInterceptor {

    @Resource(name = "tokenService")
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) throws Exception {
        String token = CookieTools.getCookieValue(httpServletRequest, "USER_TOKEN");

        YmUser ymUser = tokenService.getUserToken(token);
        //判断当前的用户是否登录

        if (null == ymUser) {
            //执行跳转到登录页面
            String url = tokenService.getLoginURL();
            StringBuffer requestURL = httpServletRequest.getRequestURL();

            httpServletResponse.sendRedirect(url + "?redirect=" + requestURL);

            return false;
        }

        httpServletRequest.setAttribute("ymUser",ymUser);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
