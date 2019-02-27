package com.geekluxun.springsecurity.service;

import com.geekluxun.springsecurity.dao.TUserMapper;
import com.geekluxun.springsecurity.entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-21 10:00
 * @Description:
 * @Other:
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private TUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        TUser user = userMapper.selectByName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("用户" + userName + "不存在");
        }

        UserDetails userDetails = new User(user.getName(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        return userDetails;
    }
}
