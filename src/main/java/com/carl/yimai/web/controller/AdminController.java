package com.carl.yimai.web.controller;

import com.carl.yimai.po.YmCategory;
import com.carl.yimai.service.CategoryService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 管理员才能处理的controller
 *
 * 当前的controller会被拦截
 * <p>Title: com.carl.yimai.web.controller AdminController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/26 21:59
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin/manage")
public class AdminController {

    @Resource(name = "categoryService")
    private CategoryService categoryService;

    @RequestMapping("/addCategory")
    @ResponseBody
    public Result addCategory(HttpServletRequest request,YmCategory category){

        String userId = (String) request.getAttribute("userId");

        Result result = categoryService.addCategory(userId, category);

        return result;
    }

}
