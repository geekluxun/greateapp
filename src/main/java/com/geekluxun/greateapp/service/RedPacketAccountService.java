package com.geekluxun.greateapp.service;

import java.math.BigDecimal;

/**
 * Created by luxun on 2018/03/05.
 */
public interface RedPacketAccountService {
    BigDecimal getRedPacketAccountByUserId(long userId);
}
