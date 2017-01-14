package com.carl.yimai.service.impl;

import cn.carl.string.StringTools;
import com.carl.yimai.mapper.YmWalletMapper;
import com.carl.yimai.po.YmWallet;
import com.carl.yimai.po.YmWalletExample;
import com.carl.yimai.pojo.WalletActionInfo;
import com.carl.yimai.service.WalletActionService;
import com.carl.yimai.service.WalletService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 钱包服务实现
 * <p>Title: com.carl.yimai.service.impl WalletServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/1/12 18:55
 * @Version 1.0
 */
@Service("walletService")
public class WalletServiceImpl implements WalletService {

    @Resource(name = "ymWalletMapper")
    private YmWalletMapper walletMapper;

    @Resource(name = "walletActionService")
    private WalletActionService actionService;

    /**
     * 根据用户的id查询当前的钱包是否存在
     *
     * @param userId
     * @return
     */
    private YmWallet getYmWallet(String userId) {
        YmWalletExample example = new YmWalletExample();
        YmWalletExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userId);
        List<YmWallet> ymWallets =
                walletMapper.selectByExample(example);

        if (null == ymWallets || ymWallets.size() == 0) {
            return null;
        }
        //返回此条记录
        return ymWallets.get(0);
    }

    /**
     * 根据用户的id来查询钱包的状态,如果没有,就插入当前用户的钱包的实例
     *
     * @param userId
     * @return
     */
    @Override
    public Result insertWallet(String userId) {
        YmWallet wallet = this.getYmWallet(userId);
        //如果数据库中没有该用户的信息,插入信息
        if (null == wallet) {
            YmWallet ymWallet = new YmWallet();

            ymWallet.setId(StringTools.uuid());
            ymWallet.setRemain(0);
            ymWallet.setStatus(1);
            ymWallet.setCreated(new Date());
            ymWallet.setUpdated(new Date());
            ymWallet.setUserid(userId);
            walletMapper.insert(ymWallet);

            return Result.ok();
        }
        //如果有,需要更新当前钱包的状态
        return updateWalletStatus(userId, 1);
    }

    @Override
    public Result updateWalletStatus(String userId, int status) {
        YmWallet wallet = this.getYmWallet(userId);
        if (null != wallet) {
            wallet.setStatus(status);
            return Result.ok();
        }
        return Result.error("没有查询到指定的内容");
    }

    /**
     * 用户对账户余额进行充值
     * @param userId
     * @param amount
     * @return
     */
    @Override
    public Result recharge(String userId, Integer amount) {
        YmWallet ymWallet = this.getYmWallet(userId);
        if (null != ymWallet) {
            //更新用户的钱包信息
            Integer remain = ymWallet.getRemain();
            ymWallet.setUpdated(new Date());
            int newRemain = remain + amount;
            ymWallet.setRemain(newRemain);
            walletMapper.updateByPrimaryKeySelective(ymWallet);

            //插入用户的消费记录到消费行为表中
            WalletActionInfo info = new WalletActionInfo();
            info.setStatus(1);
            info.setFee(amount);
            info.setSubject("充值");
            info.setWalletId(ymWallet.getId());
            actionService.insertAction(info);

            return Result.ok();
        }
        return Result.error("没有相关的信息");
    }
}
