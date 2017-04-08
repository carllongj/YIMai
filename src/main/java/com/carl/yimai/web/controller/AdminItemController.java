package com.carl.yimai.web.controller;

import cn.carl.page.PageResult;
import com.carl.yimai.adminmapper.AdminMapper;
import com.carl.yimai.po.YmCategory;
import com.carl.yimai.po.YmItem;
import com.carl.yimai.po.YmItemDesc;
import com.carl.yimai.pojo.AdminItemCondition;
import com.carl.yimai.pojo.CateInfo;
import com.carl.yimai.pojo.ItemInfo;
import com.carl.yimai.service.AdminService;
import com.carl.yimai.service.CategoryService;
import com.carl.yimai.service.ItemDescService;
import com.carl.yimai.service.ItemService;
import com.carl.yimai.web.utils.ItemCondition;
import com.carl.yimai.web.utils.Result;
import com.carl.yimai.web.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 管理员管理商品的controller
 * <p>Title: com.carl.yimai.web.controller AdminItemController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/1/4 16:05
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin/manage/item")
public class AdminItemController {

    @Resource(name = "itemService")
    private ItemService itemService;

    @Resource(name = "itemDescService")
    private ItemDescService descService;

    @Resource(name = "adminService")
    private AdminService adminService;

    @Resource(name = "categoryService")
    private CategoryService categoryService;


    /**
     * 管理员可以更新商品的信息
     * 正常测试结果 √
     * @param request
     * @param itemInfo
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result updateItem(HttpServletRequest request, ItemInfo itemInfo){
        String adminId = Utils.getAdminId(request);
        Result result = itemService.updateItem(itemInfo, adminId);
        return result;
    }

    /**
     * 管理员查询所有的商品的信息
     * 用户查询结果返回页面,管理员返回json数据
     * 正常测试结果 √
     * @param pageCount
     * @return
     */
    @RequestMapping("/show/{pageCount}")
    @ResponseBody
    public PageResult<HashMap> showItems(AdminItemCondition itemCondition,
                                         @PathVariable Integer pageCount){
        PageResult<HashMap> pageResult = adminService.selectItemsList(itemCondition, pageCount);
        return pageResult;
    }


    /**=============     商品的分类管理    ================*/


    /**
     * 校验分类的名称是否存在
     * @param name
     * @return
     */
    @RequestMapping("/check/cate.action")
    @ResponseBody
    public Result checkCateName(String name){
        Result result = adminService.checkItemCate(name);
        return result;
    }


    /**
     * 管理员新增一个分类
     * @param request
     * @param info
     * @return
     */
    @RequestMapping("/add/category.action")
    @ResponseBody
    public Result addCategory(HttpServletRequest request, CateInfo info){
        String adminId = Utils.getAdminId(request);
        YmCategory category = new YmCategory();
        BeanUtils.copyProperties(info,category);
        Result result = categoryService.addCategory(adminId, category);
        return result;
    }

    /**
     * 分页查询所有的分类的信息
     * @param page
     * @return
     */
    @RequestMapping("/all/category.action")
    @ResponseBody
    public PageResult<YmCategory> getCategoryList(@RequestParam(defaultValue = "1") Integer page){
        PageResult<YmCategory> result = categoryService.selectCategoryList(page);
        return result;
    }

    @RequestMapping("/category/del.action")
    @ResponseBody
    public Result deleteCategory(HttpServletRequest request,String id){
        Long cateId;
        try{
            cateId = Long.parseLong(id);
        }catch (Exception e){
            return Result.error("参数不合法");
        }

        String adminId = Utils.getAdminId(request);
        Result result = categoryService.deleteCategory(adminId, cateId);
        return result;
    }

    /** ===============    商品的详细信息管理     ===============      */


    /**
     * 管理员获取所有的商品的详细的描述信息
     * 正常测试结果 √
     * @param page
     * @return
     */
    @RequestMapping("/desc/check/{page}")
    @ResponseBody
    public PageResult<YmItemDesc> getDescUnchecked(@PathVariable @RequestParam(defaultValue = "1") Integer page){
        PageResult<YmItemDesc> pageResult = descService.getUncheckedDesc(page);
        return pageResult;
    }

    /**
     * 当用户的详细描述的审核通过之后,可以给用户发送邮件
     * 正常测试结果 √
     * @param request 请求信息
     * @param itemDescId 商品的详细的描述
     * @return
     */
    @RequestMapping("/desc/update/state")
    @ResponseBody
    public Result updateState(HttpServletRequest request,String itemDescId){
        String adminId = Utils.getAdminId(request);
        Result result = descService.updateDescState(adminId,itemDescId);
        return result;
    }
}
