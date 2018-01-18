package com.geekluxun.greateapp.controller;

import com.geekluxun.greateapp.constant.ResponseCode;
import com.geekluxun.greateapp.constant.SexEnum;
import com.geekluxun.greateapp.dto.CommonResponseDto;
import com.geekluxun.greateapp.dto.TestDto;
import com.geekluxun.greateapp.dto.UserDto;
import com.geekluxun.greateapp.entity.TUser;
import com.geekluxun.greateapp.mq.kafka.producer.Producer;
import com.geekluxun.greateapp.service.UserService.UserService;
import com.geekluxun.greateapp.zookeeper.ZkServiceTest;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Math.abs;

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


    @ApiOperation(value = "主接口", notes = "无", produces = "application/json",consumes = "application/json")
    @RequestMapping(value = "/main.json", method = RequestMethod.POST)
    public Object mainPage(@RequestBody @Valid UserDto para, BindingResult result, HttpServletRequest request , HttpServletResponse response) {

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
            userService.addUser(user);
            dto.setData(user);
        } catch (Exception e) {
            logger.error("========= mainPage ========== ",e);
            dto.setResult(false);
            dto.setErrcode(ResponseCode.RET_SERVER_EXCEPTION.getErrcode());
            dto.setErrmsg(ResponseCode.RET_SERVER_EXCEPTION.getErrmsg());
        }

        return dto;
    }

    @ApiOperation(value = "Kafka测试接口", notes = "无", produces = "application/json",consumes = "application/json")
    @RequestMapping(value = "/kafka/{topic}.json", method = RequestMethod.POST)
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

    /**
     * CrossOrigin解决浏览器跨域问题
     * @param params
     * @return
     */
    @ApiOperation(value = "zookeeper测试接口", notes = "无", produces = "application/json",consumes = "application/json")
    @CrossOrigin(origins = "*",allowedHeaders = "*",maxAge = 7200, methods = {RequestMethod.POST,RequestMethod.GET,RequestMethod.OPTIONS})
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

    //@ApiOperation(value = "test接口", notes = "无")
    @RequestMapping(value = "/test" , method = RequestMethod.GET)
    public void testGet(){
        logger.info("=========  testGet ============");
    }


    @RequestMapping(value = "/test2" , method = RequestMethod.POST)
    public Object testQuery(){
        CommonResponseDto  dto = new CommonResponseDto();

        Date before = new Date();
        before.setTime(new Date().getTime() - (1000*60*60*24*3L));

        userService.queryByTime(before);
        return  dto;
    }

    TUser user;

    @RequestMapping(value = "/test3" , method = RequestMethod.GET)
    public Object test3(){
        CommonResponseDto  responseDto = new CommonResponseDto();



        ExecutorService exec = Executors.newFixedThreadPool(100);
        BatchUpdateUser task = new BatchUpdateUser();
        for (int i = 0; i < 1000; i++){
            exec.execute(task);
        }
        //new Thread(new BatchUpdateUser()).start();
        return responseDto;
    }

    private class BatchUpdateUser implements Runnable {

        @Override
        public void run() {
            logger.info("threadId:{}", Thread.currentThread().getId());
            Random random = new Random();
            user = userService.queryById(89L);
            user.setPassword(random.nextInt() + "");
            try {
                Thread.sleep(abs(new Random().nextInt(1000)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            try {

                int count = userService.updateUser(user);

//            }catch (Exception e){
//                e.printStackTrace();
//            }

            logger.info("======updateUser======{}",count );
        }
    }


    @RequestMapping(value = "/test4" , method = RequestMethod.GET)
    public Object test4(){
        CommonResponseDto  responseDto = new CommonResponseDto();

        userService.testJdbc();

        return responseDto;
    }


    @RequestMapping(value = "/test5" , method = RequestMethod.GET)
    public Object test5(){
        CommonResponseDto  responseDto = new CommonResponseDto();

        TestDto dto = new TestDto();
        dto.setAge(11);
        dto.setAmount(new BigDecimal("100"));
        dto.setBorn(new Date());
        dto.setSex(SexEnum.WORMAN);

        responseDto.setData(dto);

        return responseDto;
    }

}
