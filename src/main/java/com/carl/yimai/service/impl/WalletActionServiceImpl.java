package com.carl.yimai.service.impl;

import cn.carl.page.PageResult;
import com.carl.yimai.mapper.YmWalletActionMapper;
import com.carl.yimai.po.YmWalletAction;
import com.carl.yimai.po.YmWalletActionExample;
import com.carl.yimai.pojo.WalletActionInfo;
import com.carl.yimai.service.WalletActionService;
import com.carl.yimai.web.utils.Result;
import com.carl.yimai.web.utils.Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: com.carl.yimai.service.impl WalletActionServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/1/14 18:36
 * @Version 1.0
 */
@Service("walletActionService")
public class WalletActionServiceImpl implements WalletActionService {

    @Resource(name = "ymWalletActionMapper")
    private YmWalletActionMapper actionMapper;

    @Value("${WALLET_ACTION_QUERY_ROWS}")
    private Integer WALLET_ACTION_QUERY_ROWS;

    /**
     * 用户添加一条消费行为的记录
     * @param walletActionInfo
     * @return
     */
    @Override
    public Result insertAction(WalletActionInfo walletActionInfo) {
        YmWalletAction action = new YmWalletAction();
        action.setId(Utils.getOrderId());
        BeanUtils.copyProperties(walletActionInfo,action);
        action.setCreated(new Date());
        action.setUpdated(new Date());
        actionMapper.insert(action);
        return Result.ok();
    }

    @Override
    public Result getActions(String walletId,int page) {
        PageHelper.startPage(page,WALLET_ACTION_QUERY_ROWS);
        //创建查询条件
        YmWalletActionExample example = new YmWalletActionExample();
        YmWalletActionExample.Criteria criteria = example.createCriteria();
        criteria.andWalletidEqualTo(walletId);
        List<YmWalletAction> actions = actionMapper.selectByExample(example);
        PageInfo<YmWalletAction> pageInfo = new PageInfo<YmWalletAction>(actions);
        Long total = pageInfo.getTotal();
        PageResult<YmWalletAction> pageResult = new PageResult<YmWalletAction>(total,
                WALLET_ACTION_QUERY_ROWS,actions);
        return Result.ok(pageResult);
    }
}
