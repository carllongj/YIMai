package com.carl.yimai.service;

import com.carl.yimai.po.YmCategory;
import com.carl.yimai.web.utils.Result;

/**
 * 商品信息分类service
 * <p>Title: com.carl.yimai.service</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/26 16:58
 * @Version 1.0
 */
public interface CategoryService {


    /**
     * 查询指定分类信息的商品
     * @param cid
     * @return
     */
    Result findCategory(Long cid);

    /**
     * 查询所有商品的分类信息列表
     * @return
     */
    Result selectCategoryList();

    /**
     *修改分类的信息
     * @param userId 管理员的id
     * @param ymCategory
     * @return
     */
    Result updateCategory(String userId,YmCategory ymCategory);

    /**
     * 新增分类信息
     * @param userId
     * @param category
     * @return
     */
    Result addCategory(String userId,YmCategory category);

    /**
     * 删除指定id的信息,只有当此分类下没有商品信息后才能逻辑删除
     * @param userId
     * @param categoryId
     * @return
     */
    Result deleteCategory(String userId,Long categoryId);
}
