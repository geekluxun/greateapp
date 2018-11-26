package com.geekluxun.greateapp.dto;

import com.geekluxun.greateapp.common.BaseDto;

/**
 * Created by luxun on 2017/10/20.
 */
public class CommonResponseDto extends BaseDto {

    public Boolean result;
    public Integer errcode;
    public String errmsg;
    public Object data;


    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Integer getErrcode() {
        return errcode;
    }


    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
