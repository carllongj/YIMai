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


    @Override
    public Result checkRemain(String userId, Integer money) {
        YmWallet wallet = this.getYmWallet(userId);

        if (null == wallet || 0 == wallet.getStatus()){
            return Result.error("还没有开通钱包的功能");
        }

        if (wallet.getRemain() - money < 0){
            return Result.error("当前的余额不足");
        }

        return Result.ok();
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

            insertWalletAction(ymWallet.getId(),1,amount,"充值");
            return Result.ok();
        }
        return Result.error("没有相关的信息");
    }

    /**
     * 用户查询当前账户的余额
     * @param userId 用户的id
     * @return
     */
    @Override
    public Result getCountRemain(String userId) {
        YmWallet ymWallet = getYmWallet(userId);
        if (null != ymWallet) {
            return Result.ok(ymWallet);
        }
        return Result.error("没有相关的信息");
    }

    /**
     * 用户用于支付
     * @param userId
     * @param to
     * @return
     */
    @Override
    public Result payment(String userId, String to,Integer amount) {
        return payment(userId, to,"购买商品","出售商品",amount);
    }

    /**
     * 插入用户的消费记录
     * @param walletId 钱包的id
     * @param amount 金额
     * @param subject 消费主题
     */
    private void insertWalletAction(String walletId,Integer state,
                                    Integer amount,String subject){

        //插入用户的消费记录到消费行为表中
        WalletActionInfo info = new WalletActionInfo();
        info.setStatus(1);
        info.setState(state);
        info.setFee(amount);
        info.setSubject(subject);
        info.setWalletId(walletId);
        actionService.insertAction(info);
    }

    /**
     * 用户向其他用户进行支付
     * 无论支付成功或者是失败,由controller在外部来完成调用
     * 降低事务回滚的可能性
     * @param userId
     * @param toUserId
     * @param fromSubject
     * @param toSubject
     * @param amount
     * @return
     */
    private Result payment(String userId,String toUserId,String fromSubject,
                             String toSubject,Integer amount){
                YmWallet from = getYmWallet(userId);
                YmWallet to = getYmWallet(toUserId);
                if (null != from && null != to) {
                    Result result = this.subtract(from, amount, fromSubject);
                    if (!result.isStatus()) {
                        return result;
                    }
                    Result add = this.add(to, amount, toSubject);
                    return add;
                }
            return Result.error("当前的交易无效,请联系管理员");
        }

    /**
     * 用户的增加用户钱包的余额
     * @param wallet
     * @param amount
     * @param subject
     * @return
     */
    private Result add(YmWallet wallet,Integer amount,String subject){
        //增加用户的余额
        Integer add = wallet.getRemain() + amount;
        wallet.setRemain(add);
        wallet.setUpdated(new Date());
        insertWalletAction(wallet.getId(),1,amount,subject);
        walletMapper.updateByPrimaryKeySelective(wallet);

        return Result.ok();
    }

    /**
     * 减去用户账户的余额
     * @param wallet 用户的钱包对象
     * @param amount 减去账户的数量
     * @param subject 用户的消费主题
     * @return
     */
    private Result subtract(YmWallet wallet,Integer amount,String subject){
        //获取余额
        int remain = wallet.getRemain();
        if (remain < amount) {
            return Result.error("用户余额不足");
        }

        //计算和保存余额
        Integer newRemain = remain - amount;
        wallet.setRemain(newRemain);
        wallet.setUpdated(new Date());
        //保存余额信息
        walletMapper.updateByPrimaryKeySelective(wallet);
        //保存消费记录
        insertWalletAction(wallet.getId(),2,amount,subject);

        return Result.ok();
    }
}
