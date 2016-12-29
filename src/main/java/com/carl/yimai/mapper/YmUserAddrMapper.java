package com.carl.yimai.mapper;

import com.carl.yimai.po.YmUserAddr;
import com.carl.yimai.po.YmUserAddrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YmUserAddrMapper {
    int countByExample(YmUserAddrExample example);

    int deleteByExample(YmUserAddrExample example);

    int deleteByPrimaryKey(String id);

    int insert(YmUserAddr record);

    int insertSelective(YmUserAddr record);

    List<YmUserAddr> selectByExample(YmUserAddrExample example);

    YmUserAddr selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YmUserAddr record, @Param("example") YmUserAddrExample example);

    int updateByExample(@Param("record") YmUserAddr record, @Param("example") YmUserAddrExample example);

    int updateByPrimaryKeySelective(YmUserAddr record);

    int updateByPrimaryKey(YmUserAddr record);
}