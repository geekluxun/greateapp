package com.geekluxun.springdata.jpa.controller;

import com.geekluxun.springdata.jpa.entity.user.User;
import com.geekluxun.springdata.jpa.entity.user.UserRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

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
@Slf4j
public class JpaController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/saveUser")
    @ApiOperation("saveUser")
    @ResponseBody
    public Object saveUser() {
        User user = new User();
        user.setName("luxun2");
        user.setAge(50);
        userRepository.save(user);
        Date createTime = user.getCreateTime();
        return "成功";
    }


    @GetMapping(value = "/findUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("findUser")
    @ResponseBody
    public Object findUser(HttpServletRequest request, HttpServletResponse response) {
        List<User> userList = userRepository.findByName("luxun2");
        log.info("users:" + userList);
        return "成功";
    }


    @GetMapping(value = "/checkNull")
    @ApiOperation("checkNull")
    @ResponseBody
    public Object checkNull() {
        List<User> userList = userRepository.findByName(null);
        return "成功";
    }
}
