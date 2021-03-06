package com.carl.yimai.adminmapper;

import cn.carl.page.PageResult;
import com.carl.yimai.pojo.AdminItemCondition;
import com.carl.yimai.web.utils.ItemCondition;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * <p>Title: com.carl.yimai.adminmapper</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/3/24 11:26
 * @Version 1.0
 */
public interface AdminMapper {

    /**
     * 查询最近的人员总数
     * @return
     */
    Integer[] selectUserCount();

    /**
     * 查询所有的用户
     * @param page
     * @param state
     * @return
     */
    PageResult<HashMap> selectAllUser(int page, int state);

    /**
     * 查询所有的商品信息
     * @param condition
     * @param page
     * @return
     */
    PageResult<HashMap> selectItems(AdminItemCondition condition, int page);

    /**
     * 校验当前的分类的名称是否存在
     * @param name
     * @return
     */
    Result checkItemCate(String name);
}
