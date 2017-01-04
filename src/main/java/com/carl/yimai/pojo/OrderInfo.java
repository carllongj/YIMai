package com.carl.yimai.pojo;

import java.util.Date;

/**
 * 允许管理员对订单的某些信息进行修改
 * <p>Title: com.carl.yimai.pojo OrderInfo</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/1/4 12:39
 * @Version 1.0
 */
public class OrderInfo {

    /** 订单编号 */
    String orderId;

    /** 商品的状态信息 */
    private Integer status;

    /** 订单的编号的信息 */
    private String expressid;

    /** 订单的过期的时间 */
    private Date expire;

    /** 订单的完成的时间 */
    private Date finish;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExpressid() {
        return expressid;
    }

    public void setExpressid(String expressid) {
        this.expressid = expressid;
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
