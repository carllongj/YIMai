package com.carl.yimai.service;

import com.carl.yimai.po.YmItem;
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

    Result submitItem(YmItem ymItem);

}
