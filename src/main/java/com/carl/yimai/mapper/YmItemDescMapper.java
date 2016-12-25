package com.carl.yimai.mapper;

import com.carl.yimai.po.YmItemDesc;
import com.carl.yimai.po.YmItemDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YmItemDescMapper {
    int countByExample(YmItemDescExample example);

    int deleteByExample(YmItemDescExample example);

    int deleteByPrimaryKey(String id);

    int insert(YmItemDesc record);

    int insertSelective(YmItemDesc record);

    List<YmItemDesc> selectByExampleWithBLOBs(YmItemDescExample example);

    List<YmItemDesc> selectByExample(YmItemDescExample example);

    YmItemDesc selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YmItemDesc record, @Param("example") YmItemDescExample example);

    int updateByExampleWithBLOBs(@Param("record") YmItemDesc record, @Param("example") YmItemDescExample example);

    int updateByExample(@Param("record") YmItemDesc record, @Param("example") YmItemDescExample example);

    int updateByPrimaryKeySelective(YmItemDesc record);

    int updateByPrimaryKeyWithBLOBs(YmItemDesc record);

    int updateByPrimaryKey(YmItemDesc record);
}