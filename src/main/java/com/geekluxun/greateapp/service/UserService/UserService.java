package com.geekluxun.greateapp.service.UserService;

import com.geekluxun.greateapp.entity.TUser;
import com.geekluxun.greateapp.execption.MyException;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by luxun on 2017/9/3.
 */
public interface UserService {

    void addUser(TUser user) throws MyException;

    void addUser2(TUser user);

    Boolean isSucceed();

    void exceptionTest() throws Exception;

    void testInnerMethodCall();

    void testString(String argc, Map argc2);

    void testAopArgsAnnotation(TUser user, TUser user2);

    List<TUser> queryByTime(Date beforeTime);

    TUser queryById(long id);

    int updateUser(TUser user);

    void testJdbc();
}

