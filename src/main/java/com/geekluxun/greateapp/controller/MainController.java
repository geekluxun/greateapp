package com.geekluxun.greateapp.controller;

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
        CommonResponseDto dto = new CommonResponseDto();
        if (result.getErrorCount() > 0) {
            dto.setMsg("错误啦");
            return dto;
        }

        TUser user = new TUser();


        BeanUtils.copyProperties(para, user);
        //userService.addUser(user);
        //userService.testString("hello", new HashMap());
//        userService.testAopArgsAnnotation(user, user);
        para.setName(null);
        UserDto dto1 = null;
        try {
            userService.testValidate(dto1);
        }catch (Exception e){
            logger.error("异常",e);
        }
//        userService.isSucceed();
//        try {
//            userService.exceptionTest();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //testAop("d");
        return dto;
    }

    @RequestMapping(value = "/kafka/{topic}.json")
    public Object testKafka(@PathVariable("topic") String topic){
        CommonResponseDto dto = new CommonResponseDto();
        dto.setCode("123");
        dto.setMsg("kafka");
        dto.setData("456");
        logger.info("============ send kafka msg start!!! ============");
        producer.send(topic);
        return dto;
    }

    @RequestMapping(value = "/zk.json", method = RequestMethod.POST)
    public Object testZkLock(@RequestBody Map<String , Object> params ){
        CommonResponseDto dto = new CommonResponseDto();
        dto.setCode("0000");
        dto.setMsg("成功");

        logger.info("============ 开始测试分布锁 ===========");
        try {
            zkServiceTest.testLock((String) params.get("path"));
        } catch (Exception e) {
            logger.error(" ==========  调用失败2！ ========== ", e);
            dto.setMsg("服务器返回异常！");
            dto.setCode("1111");
        }
        return dto;
    }





}
