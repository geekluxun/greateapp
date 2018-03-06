package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.dto.RedPacketTradeOrderDto;
import org.mengyun.tcctransaction.api.Compensable;

/**
 * Created by luxun on 2018/03/05.
 */
public interface RedPacketTradeOrderService {

    @Compensable
    public String record(RedPacketTradeOrderDto tradeOrderDto);
}
