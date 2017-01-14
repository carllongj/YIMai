package com.carl.yimai.service;

import com.carl.yimai.web.utils.Result;

/**
 * 钱包Service
 * <p>Title: com.carl.yimai.service</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/1/12 18:54
 * @Version 1.0
 */
public interface WalletService {

    /**
     * 用户开通钱包的功能
     * 根据用户的id来处理开通用户钱包的
     * @param userId
     * @return
     */
    Result insertWallet(String userId);

    /**
     * 更新用户钱包开通的状态
     * @param userId
     * @param status
     * @return
     */
    Result updateWalletStatus(String userId,int status);

    /**
     * 用户对账户余额进行充值
     * @param userId
     * @param amount
     * @return
     */
    Result recharge(String userId,Integer amount);
}
