package com.geekluxun.messagemiddleware.rabbitmq.transaction.constant;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-17 14:25
 * @Description:
 * @Other:
 */
public enum  MsgStatus {
    NOT_SEND("01", "未发送"),
    SEND_NOT_CONFIRM("02", "已发送未确认"),
    SEND_CONFIRMED("03", "已发送已确认");
    
    
    private String  status;
    private String  desc;

    MsgStatus(String status, String desc){
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
