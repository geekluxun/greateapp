package com.geekluxun.greateapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.Map;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/2/2 14:00
 * Description:
 */
@Controller
public class FreemarkerController {

    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", "hello!!!");
        return "welcome";
    }


    @GetMapping("/home")
    public String welcome() {
        return "home";
    }
}




