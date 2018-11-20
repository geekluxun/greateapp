package com.geekluxun.greateapp.jdk;

import java.io.Serializable;

/**
 * Created by luxun on 2017/12/27.
 */
public class CommentDemo implements Serializable {

    private String para1;
    private String para2;

    public String getPara1() {
        return para1;
    }

    public void setPara1(String para1) {
        this.para1 = para1;
    }

    public String getPara2() {
        return para2;
    }

    public void setPara2(String para2) {
        this.para2 = para2;
    }
}
