package com.carl.yimai.service.impl;

import com.carl.yimai.mapper.YmWalletActionMapper;
import com.carl.yimai.po.YmWalletAction;
import com.carl.yimai.pojo.WalletActionInfo;
import com.carl.yimai.service.WalletActionService;
import com.carl.yimai.web.utils.Result;
import com.carl.yimai.web.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
}
