package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.dao.RedpacketAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by luxun on 2018/03/05.
 */
@Service("redPacketAccountService")
public class RedPacketAccountServiceImpl implements RedPacketAccountService {

    @Autowired
    RedpacketAccountMapper redpacketAccountMapper;

    @Override
    public BigDecimal getRedPacketAccountByUserId(long userId) {
        return redpacketAccountMapper.findByUserId(userId).getBalanceAmount();
    }
}
