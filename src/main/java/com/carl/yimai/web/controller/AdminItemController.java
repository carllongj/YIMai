package com.carl.yimai.web.controller;

import cn.carl.page.PageResult;
import com.carl.yimai.po.YmItemDesc;
import com.carl.yimai.pojo.ItemInfo;
import com.carl.yimai.service.ItemDescService;
import com.carl.yimai.service.ItemService;
import com.carl.yimai.web.utils.Result;
import com.carl.yimai.web.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: com.carl.yimai.web.controller AdminItemController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/1/4 16:05
 * @Version 1.0
 */
@Controller("/admin/manage")
public class AdminItemController {

    @Resource(name = "itemService")
    private ItemService itemService;

    @Resource(name = "itemDescService")
    private ItemDescService descService;

    /**
     * 管理员可用更新商品的信息
     * @param request
     * @param itemInfo
     * @return
     */
    @RequestMapping("/item/update")
    @ResponseBody
    public Result updateItem(HttpServletRequest request, ItemInfo itemInfo){
        String adminId = Utils.getAdminId(request);
        Result result = itemService.updateItem(itemInfo, adminId);
        return result;
    }

    /**
     * 获取所有的商品的详细的描述信息
     * @param page
     * @return
     */
    @RequestMapping("/item/desc/check")
    @ResponseBody
    public PageResult<YmItemDesc> getDescUnchecked(@RequestParam(defaultValue = "1") Integer page){
        PageResult<YmItemDesc> pageResult = descService.getUncheckedDesc(page);
        return pageResult;
    }

    /**
     * 当用户的详细描述的审核通过之后,可以给用户发送邮件
     * @param request 请求信息
     * @param itemDescId 商品的详细的描述
     * @return
     */
    @RequestMapping("/item/desc/update/state")
    @ResponseBody
    public Result updateState(HttpServletRequest request,String itemDescId){
        String adminId = Utils.getAdminId(request);
        Result result = descService.updateDescState(adminId,itemDescId);
        return result;
    }
}
