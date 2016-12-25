package com.carl.yimai.service.impl;

import cn.carl.string.StringTools;
import com.carl.yimai.mapper.YmItemDescMapper;
import com.carl.yimai.po.YmItemDesc;
import com.carl.yimai.service.ItemDescService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
        //插入数据
        descMapper.insert(itemDesc);

        return Result.ok();
    }
}
