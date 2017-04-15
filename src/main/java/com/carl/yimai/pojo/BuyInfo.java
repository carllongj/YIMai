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

    /** 定义商品的标题信息 */
    private String title;

    /** 定义价格 */
    private Integer price;

    /** 定义所有者的信息 */
    private String ownerId;

    /** 定义图片的路径 */
    private String image;

    /** 订单的id信息 */
    private String orderId;

    public BuyInfo() {}

    public BuyInfo(String userId, String itemId,String ownerId,String image,String orderId) {
        this.userId = userId;
        this.itemId = itemId;
        this.ownerId = ownerId;
        this.image = image;
        this.orderId = orderId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
