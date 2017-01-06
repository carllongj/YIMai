package com.carl.yimai.service.impl;

import com.carl.yimai.mapper.YmCategoryMapper;
import com.carl.yimai.mapper.YmItemMapper;
import com.carl.yimai.po.YmCategory;
import com.carl.yimai.po.YmCategoryExample;
import com.carl.yimai.po.YmItem;
import com.carl.yimai.po.YmItemExample;
import com.carl.yimai.service.CategoryService;
import com.carl.yimai.web.utils.Result;
import com.carl.yimai.web.utils.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 分类信息service
 * <p>Title: com.carl.yimai.service.impl CategoryServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/26 17:14
 * @Version 1.0
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Resource(name = "ymCategoryMapper")
    private YmCategoryMapper categoryMapper;

    @Resource(name = "ymItemMapper")
    private YmItemMapper itemMapper;
    /**
     * 查询所有的可用的分类
     * @param cid
     * @return
     */
    @Override
    public Result findCategory(Long cid) {
        YmCategory category = categoryMapper.selectByPrimaryKey(cid);

        if (null == category || category.getStatus() == 0) {
            return Result.error("没有有关该商品信息");
        }

        return Result.ok(category);
    }

    /**
     * 查询所有的分类的列表
     * @return
     */
    @Override
    public Result selectCategoryList() {

        YmCategoryExample example = new YmCategoryExample();

        YmCategoryExample.Criteria criteria = example.createCriteria();

        criteria.andStatusEqualTo(1);

        List<YmCategory> ymCategories =
                categoryMapper.selectByExample(example);

        if (null == ymCategories || ymCategories.size() == 0){
            return Result.error("没有商品分类的信息");
        }

        return Result.ok(ymCategories);
    }

    /**
     * 管理员可用修改分类信息
     * @param userId
     * @param ymCategory
     * @return
     */
    @Override
    public Result updateCategory(String userId ,YmCategory ymCategory) {
        ymCategory.setUid(userId);
        categoryMapper.updateByPrimaryKeySelective(ymCategory);
        return Result.ok();
    }

    /**
     * 新增商品的分类信息
     * @param userId 管理的id
     * @param category
     * @return
     */
    @Override
    public Result addCategory(String userId,YmCategory category) {
        //补全对象的信息
        category.setId(Utils.getOrderId());
        category.setStatus(1);
        category.setCreated(new Date());
        category.setUpdated(new Date());
        category.setUid(userId);
        categoryMapper.insert(category);

        return Result.ok();
    }

    /**
     * 逻辑删除分类信息
     * @param userId
     * @param categoryId
     * @return
     */
    @Override
    public Result deleteCategory(String userId,Long categoryId) {

        YmCategory ymCategory = categoryMapper.selectByPrimaryKey(categoryId);

        if (null != ymCategory) {

            if (checkItem(ymCategory)){

                ymCategory.setStatus(0);
                ymCategory.setUpdated(new Date());
                ymCategory.setUid(userId);

                categoryMapper.updateByPrimaryKey(ymCategory);

                return Result.ok();
            }
            return Result.error("当前分类下还有商品信息,不能删除");
        }

        return Result.error("没有当前的分类的信息");
    }

    /**
     * 校验当前分类下是有商品信息
     * @param ymCategory
     * @return
     */
    private boolean checkItem(YmCategory ymCategory) {
        Long cid = ymCategory.getId();

        YmItemExample example = new YmItemExample();

        YmItemExample.Criteria criteria = example.createCriteria();

        criteria.andCateidEqualTo(cid);

        List<YmItem> ymItems =
                itemMapper.selectByExample(example);

        if (null != ymItems && ymItems.size() > 0){
            return false;
        }

        return true;
    }
}
