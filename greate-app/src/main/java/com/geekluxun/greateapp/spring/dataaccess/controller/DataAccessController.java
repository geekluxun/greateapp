package com.geekluxun.greateapp.spring.dataaccess.controller;

import com.geekluxun.greateapp.spring.dataaccess.servcie.DefaultFooService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-03 11:42
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/dataaccess")
public class DataAccessController {

    @Autowired
    DefaultFooService defaultFooService;

    @GetMapping("/profile")
    @ResponseBody
    @ApiOperation("profile示例")
    public Object profile() {
        defaultFooService.handle();
        return "成功";
    }
}
