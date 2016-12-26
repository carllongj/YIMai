package com.carl.yimai.web.controller;

import com.carl.yimai.po.YmItem;
import com.carl.yimai.po.YmItemDesc;
import com.carl.yimai.po.pojo.ItemInfo;
import com.carl.yimai.service.ItemService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 商品增加,修改,删除controller
 *
 * 这个controller需要被拦截器进行拦截
 * <p>Title: com.carl.yimai.web.controller ItemController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/26 0:14
 * @Version 1.0
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Resource(name = "itemService")
    private ItemService itemService;

    /**
     * 用户提交自己的商品信息
     * @param request
     * @param ymItem
     * @param ymItemDesc
     * @return
     */
    @RequestMapping("/addItem.action")
    @ResponseBody
    public Result submitItem(HttpServletRequest request,
                             YmItem ymItem, YmItemDesc ymItemDesc){
        //获取当前用户的id
        String userId = (String)request.getAttribute("userId");
        ymItem.setUid(userId);

        //提交用户的数据
        Result result = itemService.submitItem(ymItem, ymItemDesc);

        return result;
    }

    @RequestMapping("/info/{itemId}")
    public String findItem(@PathVariable String itemId,Model model){

        Result result = itemService.findItem(itemId);

        YmItem ymItem = (YmItem) result.getData();

        model.addAttribute("item",ymItem);

        return "用户可以修改的单件商品信息页面";
    }

    /**
     * 允许用户更新自己商品信息
     * @param request 请求对象
     * @param itemInfo
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result updateItem(HttpServletRequest request,ItemInfo itemInfo){

        String userId = (String) request.getAttribute("userId");

        Result result = itemService.updateItem(userId,itemInfo);

        return result;
    }

    /**
     * 允许用户删除自己的待售状态的商品
     * @param request
     * @param itemId
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteItem(HttpServletRequest request, String itemId){

        String userId = (String) request.getAttribute("userId");

        Result result = itemService.deleteItem(userId, itemId);

        return result;
    }
}
