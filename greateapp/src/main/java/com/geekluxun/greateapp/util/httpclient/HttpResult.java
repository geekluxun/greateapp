package com.geekluxun.greateapp.util.httpclient;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/2/2 15:44
 * Description:
 */
public class HttpResult {

    /**
     * 状态码
     */
    private Integer status;
    /**
     * 返回数据
     */
    private String data;

    public HttpResult() {
    }

    public HttpResult(Integer status, String data) {
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}