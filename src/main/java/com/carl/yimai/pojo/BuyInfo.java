package com.carl.yimai.pojo;

/**
 * <p>Title: com.carl.yimai.pojo BuyInfo</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/29 18:03
 * @Version 1.0
 */
public class BuyInfo {

    /** 定义购买者的id */
    private String userId;

    /** 定义商品的id信息 */
    private String itemId;

    /** 定义价格 */
    private Integer price;

    /** 定义所有者的信息 */
    private String ownerId;

    public BuyInfo() {}

    public BuyInfo(String userId, String itemId,String ownerId) {
        this.userId = userId;
        this.itemId = itemId;
        this.ownerId = ownerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
