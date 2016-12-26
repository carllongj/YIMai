package com.carl.yimai.web.controller;

import com.carl.yimai.service.CategoryService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import javax.annotation.Resource;

/**
 * 商品分类信息controller
 * <p>Title: com.carl.yimai.web.controller CategoryController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/26 22:21
 * @Version 1.0
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Resource(name = "categoryService")
    private CategoryService categoryService;

    /**
     * 查询分类信息列表
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Result selectCategoryList(){
        Result result = categoryService.selectCategoryList();

        return result;
    }

    @RequestMapping("/cate")
    @ResponseBody
    public Result findCategory(Integer cid){

        Result result = categoryService.findCategory(cid);

        return result;
    }





}
