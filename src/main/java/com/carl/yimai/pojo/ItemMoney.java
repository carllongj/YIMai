package com.carl.yimai.pojo;

import com.carl.yimai.po.YmItem;

/**
 * 转换前台和后台的金额的统一
 * <p>Title: com.carl.yimai.pojo ItemMoney</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/3/4 18:36
 * @Version 1.0
 */
public class ItemMoney extends YmItem{

    /**
     * 定义需要转换格式的金额
     */
    private String unformedPrice;

    public String getUnformedPrice() {
        return unformedPrice;
    }

    public void setUnformedPrice(String unformedPrice) {
        this.unformedPrice = unformedPrice;
    }
}
