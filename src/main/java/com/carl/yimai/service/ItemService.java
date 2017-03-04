package com.carl.yimai.service;

import cn.carl.page.PageResult;
import com.carl.yimai.po.YmItem;
import com.carl.yimai.po.YmItemDesc;
import com.carl.yimai.pojo.ItemInfo;
import com.carl.yimai.pojo.ItemMoney;
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
     * @param itemMoney
     * @return
     */
    Result submitItem(ItemMoney itemMoney, YmItemDesc itemDesc);

    /**
     * 根据id查询指定的商品
     * @param itemId
     * @return
     */
    Result findItem(String itemId);

    /**
     * 通过商品的详细描述的id来查询指定的商品的信息
     * @param descId
     * @return
     */
    Result findItemByDesc(String descId);

    /**
     * 根据指定的商品条件来查询商品信息
     * 分页查询
     * @param condition
     * @return
     */
    PageResult<YmItem> selectItemList(ItemCondition condition, Integer page);

    /**
     * 用户对商品可以根据出售者的需要进行修改
     * @return
     */
    Result updateItem(String userId, ItemInfo itemInfo);

    /**
     * 管理员可以对商品进行更新
     * @param itemInfo 管理员的更新的信息
     * @param adminId 管理员的id
     * @return
     */
    Result updateItem(ItemInfo itemInfo,String adminId);

    /**
     * 允许用户在当前商品只在待售情况下进行删除
     * @param userId
     * @param itemId
     * @return
     */
    Result deleteItem(String userId,String itemId);

    /**
     * 用户可以更新商品的状态信息
     * @return
     */
    Result updateItemStatus(String itemId,int status);

    /**
     * 更新商品的状态信息
     * @param item
     * @return
     */
    Result updateItemStatus(YmItem item);

    /**
     * 获取到最近的新添加的商品信息,来设置广告
     * @return
     */
    Result getLastestItem();

    /**
     * 获取到最近的趋势的商品信息
     * @return
     */
    Result getTrendingItems();
}
