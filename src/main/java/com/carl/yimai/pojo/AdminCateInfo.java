package com.carl.yimai.pojo;

/**
 * 管理员处理的分类信息包装类
 * <p>Title: com.carl.yimai.pojo AdminCateInfo</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/4/7 16:19
 * @Version 1.0
 */
public class AdminCateInfo {

    private CateInfo cateInfo;

    private String adminId;

    public CateInfo getCateInfo() {
        return cateInfo;
    }

    public void setCateInfo(CateInfo cateInfo) {
        this.cateInfo = cateInfo;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
