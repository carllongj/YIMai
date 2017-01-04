package com.carl.yimai.service;

import cn.carl.page.PageResult;
import com.carl.yimai.po.YmItemDesc;
import com.carl.yimai.web.utils.Result;

/**
 * 商品详情service
 * <p>Title: com.carl.yimai.service ItemDescService</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/25 23:42
 * @Version 1.0
 */
public interface ItemDescService {

    /**
     * 用户:保存用户传递的商品详情
     * @param itemDesc
     * @return
     */
    Result saveItemDesc(YmItemDesc itemDesc);

    /**
     * 用户:修改用户的商品的详细描述
     * @param itemDescId
     * @param content 修改的内容
     * @return
     */
    Result updateItemDesc(String itemDescId,String content);

    /**
     * 管理员:获取所有的没有通过审核的商品的详细描述
     * @param page
     * @return
     */
    PageResult<YmItemDesc> getUncheckedDesc(Integer page);

    /**
     * 管理员:根据商品的id来更新商品的详细描述的信息
     * @param adminId
     * @param itemDescId
     * @return
     */
    Result updateDescState(String adminId,String itemDescId);

}
