package com.carl.yimai.service;

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
     * 保存用户传递的商品详情
     * @param itemDesc
     * @return
     */
    Result saveItemDesc(YmItemDesc itemDesc);
}
