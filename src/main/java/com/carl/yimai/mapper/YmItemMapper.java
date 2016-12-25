package com.carl.yimai.mapper;

import com.carl.yimai.po.YmItem;
import com.carl.yimai.po.YmItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YmItemMapper {
    int countByExample(YmItemExample example);

    int deleteByExample(YmItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(YmItem record);

    int insertSelective(YmItem record);

    List<YmItem> selectByExample(YmItemExample example);

    YmItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YmItem record, @Param("example") YmItemExample example);

    int updateByExample(@Param("record") YmItem record, @Param("example") YmItemExample example);

    int updateByPrimaryKeySelective(YmItem record);

    int updateByPrimaryKey(YmItem record);
}