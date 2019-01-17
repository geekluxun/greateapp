package com.geekluxun.messagemiddleware.rabbitmq.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-16 16:24
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/rabbitmq")
public class RabbitMqDemoController {

    @Autowired
    ProducerDemoService produceService;

    @Autowired
    ConsumerDemoService consumerDemoService;


    @GetMapping("/send")
    @ResponseBody
    public Object sendAndReceive() {
        produceService.sendAndReceive();
        return "OK";
    }


    @GetMapping("/receive")
    @ResponseBody
    public Object receive() {
        consumerDemoService.receiveAndReply();
        return "OK";
    }

    @GetMapping("/sendAndConfirm")
    public Object sendAndConfirm() {
        produceService.sendAndConfirm();
        return "OK";
    }

    @GetMapping("/sendAndMsgPostprocessor")
    private Object sendAndMsgPostprocessor() {
        produceService.sendAndMsgPostprocessor();
        return "OK";
    }
}
