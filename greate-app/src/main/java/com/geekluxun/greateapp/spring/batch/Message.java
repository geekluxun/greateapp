/**
 *
 */
package com.geekluxun.greateapp.spring.batch;

import com.geekluxun.greateapp.entity.TUser;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/02/02 13:50
 * Description:  批处理后生成的数据
 */
public class Message {
    private Long id;
    private TUser user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }
}
