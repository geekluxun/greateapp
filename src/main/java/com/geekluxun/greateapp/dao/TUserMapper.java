package com.geekluxun.greateapp.dao;

import com.geekluxun.greateapp.entity.TUser;

import java.util.Date;
import java.util.List;

public interface TUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    List<TUser> selectByTime(Date time);


    List<TUser> selectAll();

    int updateRemind(TUser record);


}