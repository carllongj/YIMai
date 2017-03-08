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

    /** 根据最近的日期进行排序 */
    public static final Integer SORTED1 = 1;

    /** 价格从低到高进行排序 */
    public static final Integer SORTED2 = 2;

    /** 价格从高到低进行排序 */
    public static final Integer SORTED3 = 3;

    /** 根据详细的分类的id进行查询 需要页面传递*/
    private Long cid;

    /** 根据价格区间来进行查询 */
    private Integer highPrice;

    /** 根据价格区间来进行查询 */
    private Integer lowPrice;

    /** 是否需要查询任意状态的商品 */
    private Integer itemStatus;

    /** 商品的排序需求 */
    private Integer sortedBy;

    /** 商品的查询的关键字 */
    private String keyword;

    {
        this.sortedBy = SORTED1;
        this.itemStatus = 0;
    }

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

    public Integer getSortedBy() {
        return sortedBy;
    }

    public void setSortedBy(Integer sortedBy) {
        this.sortedBy = sortedBy;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
