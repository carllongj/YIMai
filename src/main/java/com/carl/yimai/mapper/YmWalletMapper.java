package com.carl.yimai.mapper;

import com.carl.yimai.po.YmWallet;
import com.carl.yimai.po.YmWalletExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YmWalletMapper {
    int countByExample(YmWalletExample example);

    int deleteByExample(YmWalletExample example);

    int deleteByPrimaryKey(String id);

    int insert(YmWallet record);

    int insertSelective(YmWallet record);

    List<YmWallet> selectByExample(YmWalletExample example);

    YmWallet selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YmWallet record, @Param("example") YmWalletExample example);

    int updateByExample(@Param("record") YmWallet record, @Param("example") YmWalletExample example);

    int updateByPrimaryKeySelective(YmWallet record);

    int updateByPrimaryKey(YmWallet record);
}