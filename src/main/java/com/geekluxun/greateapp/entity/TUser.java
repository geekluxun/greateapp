package com.geekluxun.greateapp.entity;

import com.geekluxun.greateapp.common.BaseEntity;
import com.geekluxun.greateapp.annotation.MyAnnotation;

@MyAnnotation
public class TUser extends BaseEntity{
    private Long id;

    private String name;

    private String password;

    public TUser(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public TUser() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}