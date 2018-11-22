package com.weike.system.dao;

import com.weike.system.entity.WUser;
import com.weike.system.entity.WUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface WUserMapper {
    int countByExample(WUserExample example);

    int deleteByExample(WUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(WUser record);

    int insertSelective(WUser record);

    List<WUser> selectByExample(WUserExample example);

    WUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WUser record, @Param("example") WUserExample example);

    int updateByExample(@Param("record") WUser record, @Param("example") WUserExample example);

    int updateByPrimaryKeySelective(WUser record);

    int updateByPrimaryKey(WUser record);
}