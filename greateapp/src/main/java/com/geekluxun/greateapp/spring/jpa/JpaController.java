package com.geekluxun.greateapp.spring.jpa;

import com.geekluxun.greateapp.dto.CommonResponseDto;
import com.geekluxun.greateapp.spring.jpa.demo.JpaDemoService;
import com.geekluxun.greateapp.spring.jpa.domain.User;
import com.geekluxun.greateapp.spring.jpa.domain.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-04 9:10
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/jpa")
public class JpaController {

    @Autowired
    JpaDemoService jpaDemoService;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/findUserByName")
    @ApiOperation("findUserByName")
    public Object findUserByName() {
        List<User> users = userRepository.findByName("luxun");
        return users;
    }


    @GetMapping("/save")
    @ApiOperation("save")
    public Object save() {
        User user = new User();
        user.setName("luxun");
        user.setAge(100);
        userRepository.save(user);
        return user;
    }

}
