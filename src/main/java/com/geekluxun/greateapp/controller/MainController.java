package com.geekluxun.greateapp.controller;

import com.geekluxun.greateapp.dto.CommonResponseDto;
import com.geekluxun.greateapp.dto.UserDto;
import com.geekluxun.greateapp.entity.TUser;
import com.geekluxun.greateapp.kafka.producer.Producer;
import com.geekluxun.greateapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by luxun on 2017/9/2.
 */

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    UserService userService;

    @Autowired
    Producer producer;

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

    @RequestMapping(value = "/kafka.json")
    public Object testKafka(){
        CommonResponseDto dto = new CommonResponseDto();
        dto.setCode("123");
        dto.setMsg("kafka");
        dto.setData("456");
        logger.info("============ send kafka msg start!!! ============");
        producer.send();
        return dto;
    }
}
