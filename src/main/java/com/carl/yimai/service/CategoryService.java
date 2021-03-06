package com.carl.yimai.service;

import cn.carl.page.PageResult;
import com.carl.yimai.po.YmCategory;
import com.carl.yimai.pojo.AdminCateInfo;
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
     * 根据名称来查询指定的分类
     * @param name
     * @return
     */
    Result findCategory(String name);

    /**
     * 管理员根据分页信息来查询分页列表
     * @return
     */
    PageResult<YmCategory> selectCategoryList(int page);

    /**
     * 用户查询所有商品的分类信息列表
     * @return
     */
    Result selectCategoryList();

    /**
     *修改分类的信息
     * @param adminCateInfo
     * @return
     */
    Result updateCategory(AdminCateInfo adminCateInfo);

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
