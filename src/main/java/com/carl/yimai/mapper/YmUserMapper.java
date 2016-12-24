package com.carl.yimai.mapper;

import com.carl.yimai.po.YmUser;
import com.carl.yimai.po.YmUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YmUserMapper {
    int countByExample(YmUserExample example);

    int deleteByExample(YmUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(YmUser record);

    int insertSelective(YmUser record);

    List<YmUser> selectByExample(YmUserExample example);

    YmUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YmUser record, @Param("example") YmUserExample example);

    int updateByExample(@Param("record") YmUser record, @Param("example") YmUserExample example);

    int updateByPrimaryKeySelective(YmUser record);

    int updateByPrimaryKey(YmUser record);
}