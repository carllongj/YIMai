package com.carl.yimai.service.impl;

import cn.carl.page.PageResult;
import cn.carl.string.StringTools;
import com.carl.yimai.mapper.YmItemMapper;
import com.carl.yimai.po.YmItem;
import com.carl.yimai.po.YmItemDesc;
import com.carl.yimai.po.YmItemExample;
import com.carl.yimai.po.pojo.ItemInfo;
import com.carl.yimai.service.ItemDescService;
import com.carl.yimai.service.ItemService;
import com.carl.yimai.web.utils.ItemCondition;
import com.carl.yimai.web.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public Result submitItem(YmItem ymItem,YmItemDesc itemDesc) {
        //补全类的信息
        ymItem.setId(StringTools.uuid());

        //保存商品详情
        itemDescService.saveItemDesc(itemDesc);
        String itemDescId = itemDesc.getId();
        ymItem.setDesc(itemDescId);

        ymItem.setStatus(0);
        ymItem.setCreated(new Date());
        ymItem.setUpdated(new Date());

        itemMapper.insert(ymItem);
        return Result.ok();
    }

    /**
     * 根据id来指定查询某一类商品
     * @param itemId
     * @return
     */
    @Override
    public Result findItem(String itemId) {

        YmItem ymItem = itemMapper.selectByPrimaryKey(itemId);

        return Result.ok(ymItem);
    }

    /**
     * 对用户查询的商品进行分页展示
     * @param condition
     * @param page
     * @return
     */
    @Override
    public Result selectItemList(ItemCondition condition, Integer page) {
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
        return Result.ok(pageResult);
    }

    /**
     * 允许用户修改自己的商品
     * @param itemInfo
     * @return
     */
    @Override
    public Result updateItem(ItemInfo itemInfo) {

        YmItem ymItem = new YmItem();

        BeanUtils.copyProperties(itemInfo,ymItem);

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
             criteria.andStatusEqualTo(itemCondition.getItemStatus());
        }else{
            criteria.andStatusEqualTo(itemCondition.getItemStatus());
        }

        return example;
    }

}
