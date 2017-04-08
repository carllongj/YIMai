package com.carl.yimai.pojo;

/**
 * 分类信息的插入数据
 * <p>Title: com.carl.yimai.pojo CateInfo</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/4/7 13:54
 * @Version 1.0
 */
public class CateInfo {

    /** 分类的名称 */
    private String name;

    /** 图标的名称 */
    private String icon;

    /** 分类信息 */
    private Long cateId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }
}
