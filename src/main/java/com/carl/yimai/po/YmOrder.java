package com.carl.yimai.po;

import java.util.Date;

public class YmOrder {
    private Integer id;

    private Integer status;

    private String sellerid;

    private String buyerid;

    private Integer price;

    private String comment;

    private Integer expressid;

    private Date created;

    private Date expire;

    private Date finish;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid == null ? null : sellerid.trim();
    }

    public String getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid == null ? null : buyerid.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getExpressid() {
        return expressid;
    }

    public void setExpressid(Integer expressid) {
        this.expressid = expressid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }
}