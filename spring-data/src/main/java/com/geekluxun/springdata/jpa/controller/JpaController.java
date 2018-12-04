package com.geekluxun.springdata.jpa.controller;

import com.geekluxun.springdata.jpa.entity.user.User;
import com.geekluxun.springdata.jpa.entity.user.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-04 17:12
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/jpa")
public class JpaController {
//    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/saveUser")
    @ApiOperation("测试")
    @ResponseBody
    public Object saveUser(){
        User user = new User();
        user.setName("luxun2");
        user.setAge(50);
//        userRepository.save(user);
        return "成功";
    }
}
