package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.dao.TUserMapper;
import com.geekluxun.greateapp.entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luxun on 2017/9/3.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    TUserMapper userMapper;

    @Override
    public void addUser(TUser user) {
        userMapper.insert(user);
    }
}
