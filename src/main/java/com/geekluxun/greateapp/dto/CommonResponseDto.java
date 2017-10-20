package com.geekluxun.greateapp.dto;

import java.io.Serializable;

/**
 * Created by luxun on 2017/10/20.
 */
public class CommonResponseDto implements Serializable{

    public String code;
    public String msg;
    public Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
