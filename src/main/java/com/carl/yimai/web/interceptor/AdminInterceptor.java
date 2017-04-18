package com.carl.yimai.web.interceptor;

import cn.carl.web.cookie.CookieTools;
import com.carl.yimai.po.YmUser;
import com.carl.yimai.service.TokenService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截非管理员请求的handler处理
 * <p>Title: com.carl.yimai.web.interceptor AdminIntecepter</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/26 17:05
 * @Version 1.0
 */
public class AdminInterceptor implements HandlerInterceptor {

    @Resource(name = "tokenService")
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) throws Exception {

        String token = CookieTools.getCookieValue(httpServletRequest, "USER_TOKEN");

        YmUser ymUser = tokenService.getUserToken(token);

        if (null != ymUser && ymUser.getAdmin() == 1) {
            return true;
        }

        //非管理员不能访问
        httpServletResponse.sendRedirect("/error/page/404.html");

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
