package com.carl.yimai.web.controller;

import cn.carl.page.PageResult;
import com.carl.yimai.adminmapper.AdminMapper;
import com.carl.yimai.po.YmCategory;
import com.carl.yimai.pojo.OrderInfo;
import com.carl.yimai.pojo.UserInfo;
import com.carl.yimai.service.AdminService;
import com.carl.yimai.service.CategoryService;
import com.carl.yimai.service.OrderService;
import com.carl.yimai.service.UserService;
import com.carl.yimai.web.utils.Result;
import com.carl.yimai.web.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

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

    @Resource(name = "adminService")
    private AdminService adminService;

    /**
     * 管理员:新增加分类
     *  正常测试结果 √
     * @param request
     * @param category
     * @return
     */
    @RequestMapping("/category/add")
    @ResponseBody
    public Result addCategory(HttpServletRequest request,YmCategory category){

        //设置管理员的信息
        String userId = Utils.getAdminId(request);

        Result result = categoryService.addCategory(userId, category);

        return result;
    }

    /**
     * 管理员可以更新用户的信息
     * userInfo必须从前台传递过来,其中必须包含用户的id信息
     * 正常测试结果 √
     * @param userInfo
     * @return
     */
    @RequestMapping("/user/update.action")
    @ResponseBody
    public Result updateUserInfo(UserInfo userInfo) {
        Result result = userService.updateUserInfo(userInfo);
        return result;
    }

    /**
     * 管理员可以更新订单的信息
     *  正常测试结果 √
     * @param orderInfo
     * @return
     */
    @RequestMapping("/order/update.action")
    @ResponseBody
    public Result updateOrder(OrderInfo orderInfo) {
        Result result = orderService.updateOrder(orderInfo);
        return result;
    }

    @RequestMapping("/highcharts.action")
    @ResponseBody
    public Result getLastRegister(){
        Result result = userService.getLastestRegister();
        return result;
    }

    @RequestMapping("/user/all.action")
    @ResponseBody
    public PageResult<HashMap> getAllUsers(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "-1") Integer state){
        PageResult<HashMap> result = adminService.selectAllUser(page, state);
        return result;
    }
}