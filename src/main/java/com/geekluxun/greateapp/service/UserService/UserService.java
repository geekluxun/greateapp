package com.geekluxun.greateapp.service.UserService;

import com.geekluxun.greateapp.entity.SysUser;
import com.geekluxun.greateapp.entity.TUser;

import java.util.Map;

/**
 * Created by luxun on 2017/9/3.
 */
public interface UserService {

    void addUser(TUser user);

    Boolean isSucceed();

    void exceptionTest() throws Exception;

    void testInnerMethodCall();

    void testString(String argc, Map argc2);

    void testAopArgsAnnotation(TUser user, TUser user2);
}

