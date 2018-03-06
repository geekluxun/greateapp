package com.geekluxun.greateapp.dao;

import com.geekluxun.greateapp.entity.RedpacketAccount;

public interface RedpacketAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RedpacketAccount record);

    int insertSelective(RedpacketAccount record);

    RedpacketAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RedpacketAccount record);

    int updateByPrimaryKey(RedpacketAccount record);

    RedpacketAccount findByUserId(Long userId);
}