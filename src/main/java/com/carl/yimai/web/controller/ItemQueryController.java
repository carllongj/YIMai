package com.carl.yimai.web.controller;

import com.carl.yimai.po.YmItem;
import com.carl.yimai.po.pojo.ItemInfo;
import com.carl.yimai.service.ItemService;
import com.carl.yimai.web.utils.ItemCondition;
import com.carl.yimai.web.utils.Result;
import com.sun.javafx.sg.PGShape;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品信息查询的controller
 * 此controller不需要被拦截器进行拦截
 * <p>Title: com.carl.yimai.web.controller ItemQueryController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/26 16:04
 * @Version 1.0
 */
@Controller
@RequestMapping("/query")
public class ItemQueryController {

    @Resource(name = "itemService")
    private ItemService itemService;

    @RequestMapping("/one/{itemid}")
    public String queryItem(@PathVariable String itemId, Model model){

        Result result = itemService.findItem(itemId);

        YmItem ymItem = (YmItem) result.getData();

        model.addAttribute("item",ymItem);

        return "单间商品展示页面";
    }

    /**
     * 查询指定条件的商品信息列表
     * @param itemCondition
     * @return
     */
    @RequestMapping("/list")
    public String queryItemList(ItemCondition itemCondition,Model model,
                                @RequestParam(defaultValue = "1") Integer page){

        Result result = itemService.selectItemList(itemCondition, page);

        List<YmItem> list = (List<YmItem>) result.getData();

        model.addAttribute("itemList",list);

        return "查询的列表页面";
    }

}
