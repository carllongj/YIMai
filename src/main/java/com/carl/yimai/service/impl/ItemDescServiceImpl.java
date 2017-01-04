package com.carl.yimai.service.impl;

import cn.carl.page.PageResult;
import cn.carl.string.StringTools;
import com.carl.yimai.mapper.YmItemDescMapper;
import com.carl.yimai.po.YmItemDesc;
import com.carl.yimai.po.YmItemDescExample;
import com.carl.yimai.service.ItemDescService;
import com.carl.yimai.service.ItemService;
import com.carl.yimai.web.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 商品详情service
 * <p>Title: com.carl.yimai.service.impl ItemDescServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/25 23:56
 * @Version 1.0
 */
@Service("itemDescService")
public class ItemDescServiceImpl implements ItemDescService{

    @Resource(name = "ymItemDescMapper")
    private YmItemDescMapper descMapper;

    @Value("${ADMIN_ITEM_DESC_ROWS}")
    private Integer rows;

    @Resource(name = "itemService")
    private ItemService itemService;

    /**
     * 保存用户的商品详情
     * @param itemDesc
     * @return
     */
    @Override
    public Result saveItemDesc(YmItemDesc itemDesc) {
        //补全对象的数据
        itemDesc.setId(StringTools.uuid());
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDesc.setState(0);
        //插入数据
        descMapper.insert(itemDesc);

        return Result.ok();
    }

    /**
     * 用户:更新用户商品的详细描述
     * @param itemDescId
     * @param content
     * @return
     */
    @Override
    public Result updateItemDesc(String itemDescId,String content) {
        //创建对象保存数据
        YmItemDesc desc = new YmItemDesc();
        desc.setId(itemDescId);
        desc.setContent(content);
        desc.setState(0);
        desc.setUpdated(new Date());

        //执行更新数据
        descMapper.updateByPrimaryKeyWithBLOBs(desc);
        return Result.ok();
    }

    /**
     * 获取所有待审核的详细描述,需要分页
     * @return
     */
    @Override
    public PageResult<YmItemDesc> getUncheckedDesc(Integer page) {

        PageHelper.startPage(page,rows);
        //创建查询的条件
        YmItemDescExample example = new YmItemDescExample();
        YmItemDescExample.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(0);
        //执行查询
        List<YmItemDesc> list =
                descMapper.selectByExample(example);

        PageInfo<YmItemDesc> pageInfo = new PageInfo<YmItemDesc>(list);

        Long total = pageInfo.getTotal();

        PageResult<YmItemDesc> pageResult = new PageResult<>(total,rows,list);

        return pageResult;
    }

    /**
     * 管理员审核后需要将状态进行更新,发送邮件给用户提示已经完成审核
     * @param adminIdm
     * @param itemDescId
     * @return
     */
    @Override
    public Result updateDescState(String adminIdm,String itemDescId) {
        YmItemDesc desc = descMapper.selectByPrimaryKey(itemDescId);
        desc.setState(1);
        desc.setEditor(adminIdm);
        desc.setUpdated(new Date());

        descMapper.updateByPrimaryKeySelective(desc);

        return Result.ok();
    }
}
