package com.carl.yimai.web.utils;

/**
 * 创建查询商品的条件
 * <p>Title: com.carl.yimai.web.utils ItemCondition</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/25 19:29
 * @Version 1.0
 */
public class ItemCondition {

    /** 根据详细的分类的id进行查询 需要页面传递*/
    private Long cid;

    /** 根据价格区间来进行查询 */
    private Integer highPrice;

    /** 根据价格区间来进行查询 */
    private Integer lowPrice;

    /** 是否需要查询任意状态的商品 */
    private Integer itemStatus;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Integer getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Integer highPrice) {
        this.highPrice = highPrice;
    }

    public Integer getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Integer lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Integer getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Integer itemStatus) {
        this.itemStatus = itemStatus;
    }
}
