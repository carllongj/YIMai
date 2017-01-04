package com.carl.yimai.web.controller;

import com.carl.yimai.po.YmCategory;
import com.carl.yimai.pojo.ItemInfo;
import com.carl.yimai.pojo.OrderInfo;
import com.carl.yimai.pojo.UserInfo;
import com.carl.yimai.service.CategoryService;
import com.carl.yimai.service.ItemService;
import com.carl.yimai.service.OrderService;
import com.carl.yimai.service.UserService;
import com.carl.yimai.web.utils.Result;
import com.carl.yimai.web.utils.Utils;
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

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "orderService")
    private OrderService orderService;

    @RequestMapping("/category/add")
    @ResponseBody
    public Result addCategory(HttpServletRequest request,YmCategory category){

        //设置管理员的信息
        String userId = Utils.getAdminId(request);

        Result result = categoryService.addCategory(userId, category);

        return result;
    }

    @RequestMapping("/category/delete")
    @ResponseBody
    public Result deleteCategory(HttpServletRequest request,Integer cateId){
        String userId = Utils.getAdminId(request);
        Result result = categoryService.deleteCategory(userId, cateId);
        return result;
    }

    /**
     * 管理员可以更新用户的信息
     * userInfo必须从前台传递过来,其中必须包含用户的id信息
     * @param userInfo
     * @return
     */
    @RequestMapping("/user/update")
    @ResponseBody
    public Result updateUserInfo(UserInfo userInfo) {
        Result result = userService.updateUserInfo(userInfo);
        return result;
    }

    /**
     * 管理员可以更新订单的信息
     * @param orderInfo
     * @return
     */
    @RequestMapping("/order/update")
    @ResponseBody
    public Result updateOrder(OrderInfo orderInfo) {
        Result result = orderService.updateOrder(orderInfo);
        return result;
    }

}
