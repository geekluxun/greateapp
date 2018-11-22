package com.geekluxun.greateapp.controller;

import com.geekluxun.greateapp.spring.bean.aware.ResourceAwareExample;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-22 9:57
 * @Description:
 * @Other:
 */
@RestController("/bean")
public class SpringBeanController {
    
    @Autowired
    ResourceAwareExample resourceAwareExample;
    
    
    @ApiOperation(value = "加载资源示例接口", notes = "无", produces = "application/json", consumes = "application/json")
    @RequestMapping(value = "/loadresource.json", method = RequestMethod.POST)
    public Object loadResource(){
        resourceAwareExample.loadDemo();
        return new Object();
    }
}
