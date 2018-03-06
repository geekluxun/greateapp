package com.geekluxun.greateapp.entity;

import java.math.BigDecimal;

public class TradeOrder {
    private Long id;

    private Long selfUserId;

    private Long oppositeUserId;

    private Long merchantOrderNo;

    private BigDecimal amount;

    private String status;

    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSelfUserId() {
        return selfUserId;
    }

    public void setSelfUserId(Long selfUserId) {
        this.selfUserId = selfUserId;
    }

    public Long getOppositeUserId() {
        return oppositeUserId;
    }

    public void setOppositeUserId(Long oppositeUserId) {
        this.oppositeUserId = oppositeUserId;
    }

    public Long getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(Long merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}