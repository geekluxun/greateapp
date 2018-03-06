package com.geekluxun.greateapp.dto;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Created by luxun on 2018/03/05.
 */
public class RedPacketTradeOrderDto implements Serializable {

    private static final long serialVersionUID = 4747014387277477558L;

    private long selfUserId;

    private long oppositeUserId;

    private String orderTitle;

    private long merchantOrderNo;

    private BigDecimal amount;

    public long getSelfUserId() {
        return selfUserId;
    }

    public void setSelfUserId(long selfUserId) {
        this.selfUserId = selfUserId;
    }

    public long getOppositeUserId() {
        return oppositeUserId;
    }

    public void setOppositeUserId(long oppositeUserId) {
        this.oppositeUserId = oppositeUserId;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public long getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(long merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
