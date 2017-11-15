package com.geekluxun.greateapp.service.UserService;

import com.geekluxun.greateapp.annotation.ConsistentDateParameters;
import com.geekluxun.greateapp.dto.UserDto;
import com.geekluxun.greateapp.entity.TUser;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
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

