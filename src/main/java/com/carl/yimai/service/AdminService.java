package com.carl.yimai.service;

import cn.carl.page.PageResult;
import com.carl.yimai.pojo.AdminItemCondition;
import com.carl.yimai.web.utils.Result;

import java.util.HashMap;

/**
 * <p>Title: com.carl.yimai.service</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/4/2 12:35
 * @Version 1.0
 */
public interface AdminService {

    /**
     * 管理员查询所有的用户
     * @param page
     * @param state
     * @return
     */
    PageResult<HashMap> selectAllUser(int page,int state);

    /**
     * 管理员查询搜友的商品列表
     * @param condition
     * @param page
     * @return
     */
    PageResult<HashMap> selectItemsList(AdminItemCondition condition,int page);

    /**
     * 管理员查询分类
     * @param name
     * @return
     */
    Result checkItemCate(String name);

    /**
     * 管理员禁用用户
     * @param adminId
     * @param id
     * @return
     */
    Result forbiddenUser(String adminId,String id);
}
