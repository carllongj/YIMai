package com.carl.yimai.service;

import com.carl.yimai.web.utils.Result;

import javax.annotation.Resource;

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
     * 校验用户的余额是否足够
     * @param userId
     * @param money
     * @return
     */
    Result checkRemain(String userId,Integer money);

    /**
     * 用户对账户余额进行充值
     * @param userId
     * @param amount
     * @return
     */
    Result recharge(String userId,Integer amount);

    /**
     * 获取当前用户的账户信息
     * @param userId 用户的id
     * @return
     */
    Result getCountRemain(String userId);


    /**
     * 用户向其他用户进行支付
     * @param userId 支付的用户账户
     * @param to 转账的用户的id
     * @param amount 转账金额
     * @return
     */
    Result payment(String userId,String to,Integer amount);

    /**
     * 校验用户的钱包功能是否开通
     * @param userId
     * @return
     */
    Result checkStatus(String userId);
}
