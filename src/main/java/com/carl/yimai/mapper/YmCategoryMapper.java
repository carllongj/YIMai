package com.carl.yimai.mapper;

import com.carl.yimai.po.YmCategory;
import com.carl.yimai.po.YmCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YmCategoryMapper {
    int countByExample(YmCategoryExample example);

    int deleteByExample(YmCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YmCategory record);

    int insertSelective(YmCategory record);

    List<YmCategory> selectByExample(YmCategoryExample example);

    YmCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YmCategory record, @Param("example") YmCategoryExample example);

    int updateByExample(@Param("record") YmCategory record, @Param("example") YmCategoryExample example);

    int updateByPrimaryKeySelective(YmCategory record);

    int updateByPrimaryKey(YmCategory record);
}