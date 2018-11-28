package com.geekluxun.greateapp.spring.springmvc;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-28 13:43
 * @Description:
 * @Other:
 */
public class User2 {
    public interface WithoutPasswordView {
    }

    /**
     * 带密码的view集成不带密码的view
     */
    public interface WithPasswordView extends WithoutPasswordView {
    }

    private String username;
    private String password;

    public User2() {
    }

    public User2(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @JsonView(WithoutPasswordView.class)
    public String getUsername() {
        return this.username;
    }

    /**
     * 带密码的view因为继承WithoutPasswordView，所以view也会显示getUsername获取到name，两个都显示
     * @return
     */
    @JsonView(WithPasswordView.class)
    public String getPassword() {
        return this.password;
    }
}
