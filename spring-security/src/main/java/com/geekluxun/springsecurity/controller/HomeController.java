package com.geekluxun.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-21 11:16
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/loginsuccess")
    public String loginsuccess() {
        return "loginsuccess";
    }

    @GetMapping("/page1")
    public String page1() {
        return "page1";
    }

    @GetMapping("/page2")
    public String page2() {
        return "page2";
    }
    
    
}
