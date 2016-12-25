package com.carl.yimai.web.controller;

import com.carl.yimai.po.YmItem;
import com.carl.yimai.po.YmItemDesc;
import com.carl.yimai.service.ItemService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 商品信息controller
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
}
