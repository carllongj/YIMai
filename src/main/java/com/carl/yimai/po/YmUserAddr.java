package com.carl.yimai.po;

import java.util.Date;

public class YmUserAddr {
    private String id;

    private String addressMain;

    private String addressSecond;

    private String addressThird;

    private Date created;

    private Date updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAddressMain() {
        return addressMain;
    }

    public void setAddressMain(String addressMain) {
        this.addressMain = addressMain == null ? null : addressMain.trim();
    }

    public String getAddressSecond() {
        return addressSecond;
    }

    public void setAddressSecond(String addressSecond) {
        this.addressSecond = addressSecond == null ? null : addressSecond.trim();
    }

    public String getAddressThird() {
        return addressThird;
    }

    public void setAddressThird(String addressThird) {
        this.addressThird = addressThird == null ? null : addressThird.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}