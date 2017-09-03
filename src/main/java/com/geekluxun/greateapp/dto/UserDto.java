package com.geekluxun.greateapp.dto;

import java.io.Serializable;

/**
 * Created by luxun on 2017/9/2.
 */
public class UserDto implements Serializable{

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
