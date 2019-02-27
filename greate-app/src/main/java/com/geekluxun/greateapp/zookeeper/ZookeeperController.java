package com.geekluxun.greateapp.zookeeper;

import com.geekluxun.greateapp.dto.CommonResponseDto;
import com.geekluxun.greateapp.zookeeper.share.SharedCounterExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-02-27 13:22
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/zookeeper")
public class ZookeeperController {
    @Autowired
    SharedCounterExample example;

    @RequestMapping(value = "/sharecounter", method = RequestMethod.GET)
    public Object test8() {
        CommonResponseDto responseDto = new CommonResponseDto();

        try {
            example.testShareCounter();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDto;
    }
}
