package com.geekluxun.greateapp.dao;

import com.geekluxun.greateapp.entity.TradeOrder;

public interface TradeOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TradeOrder record);

    int insertSelective(TradeOrder record);

    TradeOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeOrder record);

    int updateByPrimaryKey(TradeOrder record);

    TradeOrder findByMerchantOrderNo(Long merchantOrderNo);
}