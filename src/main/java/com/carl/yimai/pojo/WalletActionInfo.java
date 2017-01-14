package com.carl.yimai.pojo;

/**
 * 用户消费行为的数据包装类
 * <p>Title: com.carl.yimai.pojo WalletActionInfo</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/1/14 18:32
 * @Version 1.0
 */
public class WalletActionInfo {

    /** 用户钱包id的唯一标识 */
    private String walletId;

    /** 用户当前消费行为的主题 */
    private String subject;

    /** 用户当前消费行为的金额 */
    private Integer fee;

    /** 用户此次消费行为的状态 */
    private Integer status;

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
