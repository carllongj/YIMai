package com.carl.yimai.service;

import com.carl.yimai.po.YmItem;
import com.carl.yimai.po.YmItemDesc;
import com.carl.yimai.po.pojo.ItemInfo;
import com.carl.yimai.web.utils.ItemCondition;
import com.carl.yimai.web.utils.Result;

/**
 * 商品Service
 * <p>Title: com.carl.yimai.service</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/25 18:52
 * @Version 1.0
 */
public interface ItemService {

    /**
     * 用户发布自己的商品的信息
     * @param ymItem
     * @return
     */
    Result submitItem(YmItem ymItem,YmItemDesc itemDesc);

    /**
     * 根据id查询制定的商品
     * @param itemId
     * @return
     */
    Result findItem(String itemId);

    /**
     * 根据指定的商品条件来查询商品信息
     * 分页查询
     * @param condition
     * @return
     */
    Result selectItemList(ItemCondition condition,Integer page);

    /**
     * 商品可以根据出售者的需要进行修改
     * @return
     */
    Result updateItem(ItemInfo itemInfo);
}
