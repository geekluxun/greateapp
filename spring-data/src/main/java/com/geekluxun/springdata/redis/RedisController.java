package com.geekluxun.springdata.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-02-21 9:52
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisDemo1 redisDemo1;

    @GetMapping("/stringTest")
    @ResponseBody
    public Object stringTest(@RequestParam String key, @RequestParam String value) {
        redisDemo1.stringTest(key, value);
        return "OK";
    }


    @GetMapping("/hashTest")
    @ResponseBody
    public Object redisTest() {
        redisDemo1.hashTest();
        return "OK";
    }


    @GetMapping("/listTest")
    @ResponseBody
    public Object listTest() {
        redisDemo1.listTest();
        return "OK";
    }


    @GetMapping("/setTest")
    @ResponseBody
    public Object setTest() {
        redisDemo1.setTest();
        return "OK";
    }
    

    @GetMapping("/zSetTest")
    @ResponseBody
    public Object zSetTest() {
        redisDemo1.zSetTest();
        return "OK";
    }
}
