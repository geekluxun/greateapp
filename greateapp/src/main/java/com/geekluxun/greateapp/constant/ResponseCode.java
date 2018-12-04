package com.geekluxun.greateapp.constant;

/**
 * Created by luxun on 2017/11/15.
 */
public enum ResponseCode {
    RET_SUCCESS(10000, "success", "成功"),
    RET_FAILURE(20000, "failure", "失败"),
    RET_INVALID_PARA(20001, "invalid para", "无效的参数"),
    RET_SERVER_EXCEPTION(20002, "server exception", "服务器异常");


    private Integer errcode;
    private String errmsg;
    private String desc;

    ResponseCode(Integer errcode, String errmsg, String desc) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
