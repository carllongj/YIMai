package com.carl.yimai.web.controller;

import com.carl.yimai.service.ItemDescService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 请求商品的详细的参数
 * <p>Title: com.carl.yimai.web.controller ItemDescQueryController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/3/5 12:11
 * @Version 1.0
 */
@Controller
@RequestMapping("/desc")
public class ItemDescQueryController {

    @Resource(name = "itemDescService")
    private ItemDescService itemDescService;

    @RequestMapping("/query/{descId}")
    @ResponseBody
    public Result queryDesc(@PathVariable String descId){
        Result result = itemDescService.getDescById(descId);
        return result;
    }
}
