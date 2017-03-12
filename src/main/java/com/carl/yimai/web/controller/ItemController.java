package com.carl.yimai.web.controller;

import com.carl.yimai.po.YmItem;
import com.carl.yimai.po.YmItemDesc;
import com.carl.yimai.pojo.ItemInfo;
import com.carl.yimai.pojo.ItemMoney;
import com.carl.yimai.service.ItemDescService;
import com.carl.yimai.service.ItemService;
import com.carl.yimai.service.UserService;
import com.carl.yimai.web.utils.Result;
import com.carl.yimai.web.utils.Utils;
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

    @Resource(name = "itemDescService")
    private ItemDescService descService;

    @Resource(name = "userService")
    private UserService userService;

    /**
     * 用户提交自己的商品信息
     * 正常测试结果 √
     * 页面交互 √
     * @param request
     * @param moneyInfo
     * @param ymItemDesc
     * @return
     */
    @RequestMapping("/addItem.action")
    @ResponseBody
    public Result submitItem(HttpServletRequest request,
                             ItemMoney moneyInfo, YmItemDesc ymItemDesc){
        //获取当前用户的id
//        String userId = (String)request.getAttribute("userId");
//        ymItem.setUid(userId);

        //测试用数据
        moneyInfo.setUid("123456789");

        //提交用户的数据
        Result result = itemService.submitItem(moneyInfo, ymItemDesc);

        return result;
    }

    /**
     * 根据item的id来查询卖家的信息
     * @param userId
     * @return
     */
    @RequestMapping("/seller/{userId}")
    @ResponseBody
    public Result getUserByItem(@PathVariable String userId){
        Result result = userService.getUserContactById(userId);
        return result;
    }

    /**
     * 用户根据id查询指定id的商品信息
     * 正常测试结果 √
     * @param itemId
     * @param model
     * @return
     */
    @RequestMapping("/info/{itemId}")
    public String findItem(@PathVariable String itemId,Model model){

        Result result = itemService.findItem(itemId);

        YmItem ymItem = (YmItem) result.getData();

        model.addAttribute("item",ymItem);

        return "用户可以修改的单件商品信息页面";
    }

    /**
     * 允许用户更新自己商品信息
     * 正常测试结果 √
     * @param request 请求对象
     * @param itemInfo
     * @return
     */
    @RequestMapping("/update.action")
    @ResponseBody
    public Result updateItem(HttpServletRequest request,ItemInfo itemInfo){

        String userId = (String) request.getAttribute("userId");

        Result result = itemService.updateItem(userId,itemInfo);

        return result;
    }

    /**
     * 允许用户删除自己的待售状态的商品
     * 正常测试结果 √
     * @param request
     * @param itemId
     * @return
     */
    @RequestMapping("/delete.action")
    @ResponseBody
    public Result deleteItem(HttpServletRequest request, String itemId){

        String userId = "123456789";/*(String) request.getAttribute("userId");*/

        Result result = itemService.deleteItem(userId, itemId);

        return result;
    }

    /**
     * 用户允许用户更新商品的详细描述
     * 正常测试结果 √
     * @param request
     * @param itemDescId
     * @param content 更新的内容的信息
     * @return
     */
    @RequestMapping("/updateDesc.action")
    @ResponseBody
    public Result updateDesc(HttpServletRequest request,
                             String itemDescId,String content){

        Result  r = itemService.findItemByDesc(itemDescId);

        //对不合法的修改进行处理
        if (!r.isStatus()){
            return r;
        }

        String userId = Utils.getAdminId(request);
//        String userId = "123456789";

        if (!userId.equals(((YmItem)r.getData()).getUid())) {
             return Result.error("你没有修改商品的权限");
        }

        Result result = descService.updateItemDesc(itemDescId, content);
        return result;
    }
}

