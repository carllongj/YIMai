package com.carl.yimai.web.controller;

import cn.carl.page.PageResult;
import com.carl.yimai.po.YmItem;
import com.carl.yimai.po.YmItemDesc;
import com.carl.yimai.pojo.ItemInfo;
import com.carl.yimai.service.ItemDescService;
import com.carl.yimai.service.ItemService;
import com.carl.yimai.web.utils.ItemCondition;
import com.carl.yimai.web.utils.Result;
import com.carl.yimai.web.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
     * @param page
     * @return
     */
    @RequestMapping("/show/{page}")
    @ResponseBody
    public PageResult<YmItem> showItems(ItemCondition itemCondition,
            @PathVariable @RequestParam(defaultValue = "1") Integer page){
        PageResult<YmItem> pageResult = itemService.selectItemList(itemCondition, page);
        return pageResult;
    }

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
