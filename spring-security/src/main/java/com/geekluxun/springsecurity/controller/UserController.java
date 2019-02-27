package com.geekluxun.springsecurity.controller;

import com.geekluxun.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-21 19:09
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @ResponseBody
    @PostMapping("/info")
    public Object getUserInfo(){
        userService.getUserInfo("luxun");
        return "OK";
    }

    @ResponseBody
    @PostMapping("/del")
    public Object deleteUser(){
        userService.deleteUser("luxun");
        return "OK";
    }
    
}
