package com.carl.yimai.mapper;

import com.carl.yimai.po.YmWalletAction;
import com.carl.yimai.po.YmWalletActionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YmWalletActionMapper {
    int countByExample(YmWalletActionExample example);

    int deleteByExample(YmWalletActionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YmWalletAction record);

    int insertSelective(YmWalletAction record);

    List<YmWalletAction> selectByExample(YmWalletActionExample example);

    YmWalletAction selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YmWalletAction record, @Param("example") YmWalletActionExample example);

    int updateByExample(@Param("record") YmWalletAction record, @Param("example") YmWalletActionExample example);

    int updateByPrimaryKeySelective(YmWalletAction record);

    int updateByPrimaryKey(YmWalletAction record);
}