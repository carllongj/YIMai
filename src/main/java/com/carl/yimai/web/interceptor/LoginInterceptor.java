package com.carl.yimai.web.interceptor;

import cn.carl.web.cookie.CookieTools;
import com.alibaba.fastjson.JSON;
import com.carl.yimai.po.YmUser;
import com.carl.yimai.service.TokenService;
import com.carl.yimai.web.utils.Result;
import com.carl.yimai.web.utils.Utils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录拦截器
 * <p>Title: com.carl.yimai.web LoginInterceptor</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/25 14:15
 * @Version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

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

            if (Utils.isAjaxRequest(httpServletRequest)){
                Result result = Result.error("当前用户的信息以过期,请重新登录");
                String jsonString = JSON.toJSONString(result);
                httpServletResponse.getWriter().write(jsonString);
                return false;
            }

            //执行跳转到登录页面
            String url = tokenService.getLoginURL();
            StringBuffer requestURL = httpServletRequest.getRequestURL();
            String str = httpServletRequest.getQueryString();
            if (null == str || "".equals(str)){
                str = "";
            }

            httpServletResponse.sendRedirect(url + "?redirect=" + requestURL + ("".equals(str) ? "" :  "?"  + str));
            return false;
        }

        //保存用户的信息到request域中
        httpServletRequest.setAttribute("ymUser",ymUser);

        //保存用户的唯一标识
        httpServletRequest.setAttribute("userId",ymUser.getId());

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
