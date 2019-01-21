package com.geekluxun.springsecurity.dao;


import com.geekluxun.springsecurity.entity.TUser;
import org.apache.ibatis.annotations.Param;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-21 17:22
 * @Description:
 * @Other:
 */
public interface TUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Long id);

    TUser selectByName(@Param("name") String name);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
}