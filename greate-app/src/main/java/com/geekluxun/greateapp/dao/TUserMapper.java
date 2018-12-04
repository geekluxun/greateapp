package com.geekluxun.greateapp.dao;

import com.geekluxun.greateapp.entity.TUser;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Repository使用的目的是使用springDAO统一处理数据访问异常
 */
@Repository
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