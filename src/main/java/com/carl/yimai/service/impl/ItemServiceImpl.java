package com.carl.yimai.service.impl;

import cn.carl.page.PageResult;
import cn.carl.string.StringTools;
import com.carl.yimai.mapper.YmItemMapper;
import com.carl.yimai.po.YmItem;
import com.carl.yimai.po.YmItemDesc;
import com.carl.yimai.po.YmItemDescExample;
import com.carl.yimai.po.YmItemExample;
import com.carl.yimai.pojo.ItemInfo;
import com.carl.yimai.service.ItemDescService;
import com.carl.yimai.service.ItemService;
import com.carl.yimai.web.utils.ItemCondition;
import com.carl.yimai.web.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.ResultType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 商品Service实现
 * <p>Title: com.carl.yimai.service.impl ItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/25 19:51
 * @Version 1.0
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService {

    @Resource(name = "ymItemMapper")
    private YmItemMapper itemMapper;

    @Resource(name = "itemDescService")
    private ItemDescService itemDescService;

    @Value("${ITEM_PAGE_COUNT}")
    private Integer rows;

    /**
     * 用户提交想要出售的商品信息
     * @param ymItem
     * @param itemDesc
     * @return
     */
    @Override
    public Result submitItem(YmItem ymItem,YmItemDesc itemDesc) {
        String itemId = StringTools.uuid();
        //补全类的信息
        ymItem.setId(itemId);

        String descId = StringTools.uuid();
        itemDesc.setId(descId);

        //保存商品详情
        itemDesc.setItemId(itemId);

        itemDescService.saveItemDesc(itemDesc);

        ymItem.setStatus(0);
        ymItem.setCreated(new Date());
        ymItem.setUpdated(new Date());
        ymItem.setDescid(descId);
        itemMapper.insert(ymItem);

        return Result.ok();
    }

    /**
     * 用户根据id来指定查询某一类商品
     * @param itemId
     * @return
     */
    @Override
    public Result findItem(String itemId) {

        YmItem ymItem = itemMapper.selectByPrimaryKey(itemId);

        return Result.ok(ymItem);
    }

    @Override
    public Result findItemByDesc(String descId) {
        //创建查询的条件
        YmItemExample example = new YmItemExample();
        YmItemExample.Criteria criteria = example.createCriteria();
        criteria.andDescidEqualTo(descId);

        List<YmItem> ymItems = itemMapper.selectByExample(example);

        if (null == ymItems || ymItems.size() != 1) {
            return Result.error("没有你想查询的商品的信息");
        }

        return Result.ok(ymItems.get(0));
    }

    /**
     * 对用户查询的商品进行分页展示
     * @param condition
     * @param page
     * @return
     */
    @Override
    public PageResult<YmItem> selectItemList(ItemCondition condition, Integer page) {
        //设置分页信息
        PageHelper.startPage(page,rows);

        //解析查询条件
        YmItemExample example = parseCondition(condition);

        //执行查询
        List<YmItem> itemList = itemMapper.selectByExample(example);
        PageInfo<YmItem> pageInfo = new PageInfo<YmItem>(itemList);

        Long total = pageInfo.getTotal();

        //创建返回的结果对象
        PageResult<YmItem> pageResult = new PageResult<YmItem>(total,rows,itemList);

        return pageResult;
    }


    /**
     * 允许用户修改自己的商品
     * @param userId
     * @param itemInfo
     * @return
     */
    @Override
    public Result updateItem(String userId ,ItemInfo itemInfo) {

        String itemId = itemInfo.getId();

        YmItem item = itemMapper.selectByPrimaryKey(itemId);

        if(null == item || !item.getUid().equals(userId)){
            return Result.error("你没有管理当前商品的权限");
        }

        YmItem ymItem = new YmItem();

        BeanUtils.copyProperties(itemInfo,ymItem);

        itemMapper.updateByPrimaryKeySelective(ymItem);

        return Result.ok();
    }

    /**
     * 管理员可以对商品的信息进行管理
     * @param itemInfo
     * @param adminId
     * @return
     */
    @Override
    public Result updateItem(ItemInfo itemInfo,String adminId) {

        YmItem ymItem = new YmItem();
        //对属性进行拷贝
        BeanUtils.copyProperties(itemInfo,ymItem);
        //保存管理员的信息
        ymItem.setEditor(adminId);

        itemMapper.updateByPrimaryKeySelective(ymItem);

        return Result.ok();
    }

    /**
     * 设置用户的查询的参数,返回查询的条件对象
     * @param itemCondition
     * @return
     */
    private YmItemExample parseCondition(ItemCondition itemCondition){

        YmItemExample example = new YmItemExample();
        YmItemExample.Criteria criteria = example.createCriteria();

        //是否需要查询指定的分类
        if(null != itemCondition.getCid()){
            criteria.andCateidEqualTo(itemCondition.getCid());
        }

        //是否根据价格区间来进行查询
        if(null != itemCondition.getHighPrice() && null != itemCondition.getLowPrice()){
            criteria.andPriceBetween(itemCondition.getLowPrice(),itemCondition.getHighPrice());
        }else if(null != itemCondition.getLowPrice()){
            criteria.andPriceGreaterThanOrEqualTo(itemCondition.getLowPrice());
        }else if (null != itemCondition.getHighPrice()) {
            criteria.andPriceLessThanOrEqualTo(itemCondition.getHighPrice());
        }

        //是否根据商品的状态来查询商品
        if ( null == itemCondition.getItemStatus() || 0 == itemCondition.getItemStatus()) {
             criteria.andStatusEqualTo(0);
        }else{
            criteria.andStatusEqualTo(itemCondition.getItemStatus());
        }

        return example;
    }

    /**
     * 允许用户在商品为待售的情况下进行删除商品
     * @param itemId
     * @return
     */
    @Override
    public Result deleteItem(String userId,String itemId) {

        YmItem ymItem = itemMapper.selectByPrimaryKey(itemId);

        if (null == ymItem) {
            return Result.error("没有关于当前商品的相关信息");
        }

        if (!ymItem.getUid().equals(userId)){
            return Result.error("你没有权限来操作当前的商品");
        }

        if (ymItem.getStatus() != 0) {
            return Result.error("当前的商品已经处于被售状态,无法删除商品信息");
        }

         itemMapper.deleteByPrimaryKey(itemId);

        return Result.ok();
    }

    @Override
    public Result updateItemStatus(String itemId, int status) {
        YmItem ymItem = itemMapper.selectByPrimaryKey(itemId);

        if (null != ymItem) {
            ymItem.setStatus(status);
            itemMapper.updateByPrimaryKeySelective(ymItem);
            return Result.ok();
        }

        return Result.error("没有当前相关的商品信息");
    }

    @Override
    public Result updateItemStatus(YmItem item) {
        itemMapper.updateByPrimaryKeySelective(item);
        return Result.ok();
    }
}
