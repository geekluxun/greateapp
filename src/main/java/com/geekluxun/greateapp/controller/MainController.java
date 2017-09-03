package com.geekluxun.greateapp.controller;

import com.geekluxun.greateapp.dto.UserDto;
import com.geekluxun.greateapp.entity.TUser;
import com.geekluxun.greateapp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by luxun on 2017/9/2.
 */

@RestController
public class MainController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/main.json")
    public Object mainPage(){
        TUser user = new TUser();
        UserDto dto = new UserDto();
        dto.setName("luxun");
        dto.setPassword("123123");

        BeanUtils.copyProperties(dto, user);
        userService.addUser(user);

        return  dto;
    }
}
