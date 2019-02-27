package com.geekluxun.springsecurity.service;

import com.geekluxun.springsecurity.entity.TUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-21 19:05
 * @Description:
 * @Other:
 */
@Service
@Slf4j
public class UserServiceImpl  implements UserService{


    @Override
    public TUser getUserInfo(String userName) {
        log.info("根据用户名获取用户....");
        return null;
    }

    @Override
    public void deleteUser(String userName) {
        log.info("根据用户名删除用户....");
    }
}
