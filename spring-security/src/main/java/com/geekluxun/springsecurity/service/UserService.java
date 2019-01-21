package com.geekluxun.springsecurity.service;

import com.geekluxun.springsecurity.entity.TUser;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-21 19:05
 * @Description:
 * @Other:
 */
public interface UserService {

    @PreAuthorize("isAnonymous()")
    TUser getUserInfo(String userName);
    

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    void deleteUser(String userName);
}
