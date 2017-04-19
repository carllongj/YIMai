package com.carl.yimai.service;

import cn.carl.page.PageResult;
import com.carl.yimai.po.YmWallet;
import com.carl.yimai.po.YmWalletAction;
import com.carl.yimai.pojo.WalletActionInfo;
import com.carl.yimai.web.utils.Result;

/**
 * 用户的消费的行为service
 * <p>Title: com.carl.yimai.service</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/1/14 18:21
 * @Version 1.0
 */
public interface WalletActionService {

    /**
     * 新增用户的消费行为
     * @return
     */
    Result insertAction(WalletActionInfo walletActionInfo);

    /**
     * 获取消费记录
     * @param walletId
     * @param page 当期的页数
     * @return
     */
    PageResult<YmWalletAction> getActions(String walletId, int page);

    /**
     * 根据用户的账户id来获取所有的消费记录
     * @param userId
     * @param page
     * @return
     */
    PageResult<YmWalletAction> getActionsByUserId(String userId,int page);

}
