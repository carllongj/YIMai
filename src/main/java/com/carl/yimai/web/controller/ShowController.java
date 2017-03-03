package com.carl.yimai.web.controller;

import com.alibaba.fastjson.JSON;
import com.carl.yimai.service.CategoryService;
import com.carl.yimai.service.ItemService;
import com.carl.yimai.web.utils.ItemCondition;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
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

    @Resource(name = "itemService")
    private ItemService itemService;

    /**
     * 展示系统的首页
     * 测试结果 √
     * @return
     */
    @RequestMapping("/index.action")
    public String showIndex(Model model){

        Result result = itemService.getLastestItem();
        Result trendingItems = itemService.getTrendingItems();
        //首页广告位的展示
        model.addAttribute("lastest",JSON.toJSONString(result.getData()));
        model.addAttribute("trending",JSON.toJSONString(trendingItems.getData()));

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

    /**
     * 展示发布商品信息的页面
     * @return
     */
    @RequestMapping("/post/post_ad.action")
    public String showPostAd(){
        return "post_ad";
    }
}
