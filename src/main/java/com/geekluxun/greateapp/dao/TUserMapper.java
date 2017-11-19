package com.geekluxun.greateapp.dao;

import com.geekluxun.greateapp.entity.TUser;

/**
 * Created by luxun on 2017/9/2.
 */

public interface TUserMapper {

    int deleteByPrimaryKey(Long id);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
}