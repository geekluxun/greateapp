package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.dao.TUserMapper;
import com.geekluxun.greateapp.entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luxun on 2017/9/3.
 */
//@Service  如果不指明bean的名字，默认的名字时uerServiceImpl,通过这个名字注入此bean到其他bean中
//@Service("userService33")指明名字为userService33
@Service("userService33")
public class UserServiceImpl implements UserService{
    @Autowired
    TUserMapper userMapper;

    @Override
    public void addUser(TUser user) {
        userMapper.insert(user);
    }

    @Override
    public Boolean isSucceed() {
        return false;
    }

    public void exceptionTest() throws Exception{
        throw new RuntimeException("在UserService.exceptionTest中抛出异常示例!!");
    }
}
