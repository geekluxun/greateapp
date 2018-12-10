package com.geekluxun.dto;

import java.io.Serializable;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-10 13:26
 * @Description:
 * @Other:
 */
public class Account implements Serializable {
    private static final long serialVersionUID = -461403347560529823L;

    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}