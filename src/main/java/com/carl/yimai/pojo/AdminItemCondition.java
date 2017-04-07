package com.carl.yimai.pojo;

import com.carl.yimai.web.utils.ItemCondition;
import com.carl.yimai.web.utils.Page;

/**
 * <p>Title: com.carl.yimai.pojo AdminItemCondition</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/4/7 9:10
 * @Version 1.0
 */
public class AdminItemCondition {

    /** 是否需要查询任意状态的商品 */
    private Integer passStatus;

    /** 商品的排序需求 */
    private Integer sortedBy;

    /** 分页数据 */
    private Page page;

    {
        sortedBy = ItemCondition.SORTED1;
        passStatus = -1;
    }

    public Integer getPassStatus() {
        return passStatus;
    }

    public void setPassStatus(Integer passStatus) {
        this.passStatus = passStatus;
    }

    public Integer getSortedBy() {
        return sortedBy;
    }

    public void setSortedBy(Integer sortedBy) {
        this.sortedBy = sortedBy;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
