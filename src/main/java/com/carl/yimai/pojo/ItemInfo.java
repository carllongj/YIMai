package com.carl.yimai.pojo;

import java.util.Date;

/**
 * 允许用户修改的商品的信息的包装类
 * <p>Title: com.carl.yimai.po.pojo ItemInfo</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/26 0:04
 * @Version 1.0
 */
public class ItemInfo {

    /** 商品的id信息 */
    private String id;

    /** 商品的标题 */
    private String title;

    /** 商品的价格 */
    private Integer price;

    /**商品的状态*/
    private Integer status;

    /**商品的分类的id */
    private Integer cateid;

    /** 商品的图片的url */
    private String image;

    /** 商品的更新的日期 */
    private Date updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCateid() {
        return cateid;
    }

    public void setCateid(Integer cateid) {
        this.cateid = cateid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}