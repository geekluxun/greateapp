package com.geekluxun.springdata.jpa.entity.user;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-05 14:40
 * @Description:
 * @Other:
 */
public interface CustomizedUserRepository {
    void mySave(User user);
}
