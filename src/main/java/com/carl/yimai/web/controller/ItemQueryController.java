package com.carl.yimai.web.controller;

import cn.carl.page.PageResult;
import com.alibaba.fastjson.JSON;
import com.carl.yimai.po.YmItem;
import com.carl.yimai.service.ItemService;
import com.carl.yimai.web.utils.ItemCondition;
import com.carl.yimai.web.utils.Result;
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

    /**
     * 查询单件商品的信息
     * 正常测试结果 √
     * @param itemId
     * @param model
     * @return
     */
    @RequestMapping("/one/{itemId}")
    public String queryItem(@PathVariable String itemId, Model model){

        Result result = itemService.findItem(itemId);

        YmItem ymItem = (YmItem) result.getData();

        model.addAttribute("item", JSON.toJSONString(ymItem));

        model.addAttribute("id",ymItem.getId());

        return "/single";
    }

    /**
     * 用户:查询指定条件的商品信息列表
     * 正常测试结果 √
     * @param itemCondition
     * @return
     */
    @RequestMapping("/list.action")
    public String queryItemList(ItemCondition itemCondition,Model model,
                                @RequestParam(value = "agileinfo_search") String cid,
                                @RequestParam(defaultValue = "1") Integer page){
        Long realCid;
        try{
            realCid = Long.parseLong(cid);
        }catch (Exception e){
            realCid = null;
        }

        if (null != realCid) {
            itemCondition.setCid(realCid);
        }

        PageResult<YmItem> pageResult = itemService.selectItemList(itemCondition, page);
        model.addAttribute("pageResult",JSON.toJSONString(pageResult));
        model.addAttribute("condition",JSON.toJSONString(itemCondition));
        return "all";
    }

    /**
     * ajax异步请求分页后的数据
     * @param condition
     * @param page
     * @return
     */
    @RequestMapping("/async.action")
    @ResponseBody
    public PageResult<YmItem> queryItems(ItemCondition condition,@RequestParam(defaultValue = "1") Integer page) {
        PageResult<YmItem> pageResult = itemService.selectItemList(condition, page);
        return pageResult;
    }
}
