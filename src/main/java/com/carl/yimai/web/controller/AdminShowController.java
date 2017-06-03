package com.carl.yimai.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员才能查看的页面的处理
 * <p>Title: com.carl.yimai.web.controller AdminShowController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/3/24 14:47
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin/show")
public class AdminShowController {

    @RequestMapping("/index")
    public String showIndex(){
        return "ad_index";
    }

    @RequestMapping("/allusers")
    public String showAllUser(){
        return "ad_all_users";
    }

    @RequestMapping("/inactive")
    public String showInactiveUser(){
        return "ad_inactive_user";
    }

    @RequestMapping("/allitems")
    public String showAllItems(){
        return "ad_all_items";
    }

    @RequestMapping("/notpass")
    public String showNotPassItems(){
        return "ad_not_pass_items";
    }

    @RequestMapping("/addcate")
    public String showAddCate(){
        return "ad_addcate";
    }

    @RequestMapping("/allcate")
    public String showAllCate(){
        return "ad_all_cate";
    }

    @RequestMapping("/allorders")
    public String showAllOrders(){
        return "ad_all_orders";
    }
}
