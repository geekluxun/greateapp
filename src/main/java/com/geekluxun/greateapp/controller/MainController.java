package com.geekluxun.greateapp.controller;

import com.geekluxun.greateapp.constant.ResponseCode;
import com.geekluxun.greateapp.dto.CommonResponseDto;
import com.geekluxun.greateapp.dto.UserDto;
import com.geekluxun.greateapp.entity.TUser;
import com.geekluxun.greateapp.kafka.producer.Producer;
import com.geekluxun.greateapp.service.UserService.UserService;
import com.geekluxun.greateapp.zookeeper.ZkServiceTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by luxun on 2017/9/2.
 */

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    @Qualifier("userService33")
    UserService userService;

    @Autowired
    Producer producer;

    @Autowired
    ZkServiceTest zkServiceTest;


    @RequestMapping(value = "/main.json", method = RequestMethod.POST)
    public Object mainPage(@RequestBody @Valid UserDto para, BindingResult result) {

        CommonResponseDto  dto = new CommonResponseDto();
        dto.setResult(true);

        try {

            if (result.getErrorCount() > 0) {
                String msg = result.getAllErrors().iterator().next().getDefaultMessage();
                dto.setErrmsg(msg);
                dto.setErrcode(ResponseCode.RET_INVALID_PARA.getErrcode());
                logger.error("=========== mainPage ==========" + msg);
                return dto;
            }

            TUser user = new TUser();
            BeanUtils.copyProperties(para, user);

            dto.setData(user);
        } catch (Exception e) {
            logger.error("========= mainPage ========== ",e);
            dto.setResult(false);
            dto.setErrcode(ResponseCode.RET_SERVER_EXCEPTION.getErrcode());
            dto.setErrmsg(ResponseCode.RET_SERVER_EXCEPTION.getErrmsg());
        }

        return dto;
    }

    @RequestMapping(value = "/kafka/{topic}.json")
    public Object testKafka(@PathVariable("topic") String topic) {
        CommonResponseDto dto = new CommonResponseDto();
        dto.setResult(true);
        dto.setErrcode(123);
        dto.setErrmsg("kafka");
        dto.setData("456");
        logger.info("============ send kafka msg start!!! ============");
        producer.send(topic);
        return dto;
    }

    @RequestMapping(value = "/zk.json", method = RequestMethod.POST)
    public Object testZkLock(@RequestBody Map<String, Object> params) {
        CommonResponseDto dto = new CommonResponseDto();
        dto.setResult(true);
        dto.setErrcode(0000);
        dto.setErrmsg("成功");

        logger.info("============ 开始测试分布锁 ===========");
        try {
            zkServiceTest.testLock((String) params.get("path"));
        } catch (Exception e) {
            logger.error(" ==========  调用失败2！ ========== ", e);
        }
        return dto;
    }

    @RequestMapping(value = "test")
    public void testGet(){
        logger.info("=========  testGet ============");
    }

}
