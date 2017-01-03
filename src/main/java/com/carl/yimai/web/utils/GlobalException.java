package com.carl.yimai.web.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: com.carl.yimai.web.utils GlobalException</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/24 17:13
 * @Version 1.0
 */
public class GlobalException implements HandlerExceptionResolver {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {

        message = ((StringUtils.hasText(this.message)) ? message : "系统未知错误,请稍候重试");

        if (StringUtils.hasText(e.getMessage())) {
            this.message = e.getMessage();
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/error/error");
        mv.addObject("message",message);
        return mv;
    }
}
