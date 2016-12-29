package com.carl.yimai.mapper;

import com.carl.yimai.po.YmOrder;
import com.carl.yimai.po.YmOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YmOrderMapper {
    int countByExample(YmOrderExample example);

    int deleteByExample(YmOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YmOrder record);

    int insertSelective(YmOrder record);

    List<YmOrder> selectByExample(YmOrderExample example);

    YmOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YmOrder record, @Param("example") YmOrderExample example);

    int updateByExample(@Param("record") YmOrder record, @Param("example") YmOrderExample example);

    int updateByPrimaryKeySelective(YmOrder record);

    int updateByPrimaryKey(YmOrder record);
}