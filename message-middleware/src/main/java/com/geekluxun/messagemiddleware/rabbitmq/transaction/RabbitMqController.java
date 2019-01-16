package com.geekluxun.messagemiddleware.rabbitmq.transaction;

import com.geekluxun.messagemiddleware.rabbitmq.transaction.consumer.ConsumerService;
import com.geekluxun.messagemiddleware.rabbitmq.transaction.producer.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.x509.OIDMap;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-16 16:24
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/rabbitmq")
public class RabbitMqController {
    
    @Autowired
    ProduceService produceService;
    
    @Autowired
    ConsumerService consumerService;
    
    
    @GetMapping("/send")
    @ResponseBody
    public Object sendAndReceive(){
        produceService.sendAndReceive();
        return "OK";
    } 
    
    
    @GetMapping("/receive")
    @ResponseBody
    public Object receive(){
        consumerService.receiveAndReply();
        return "OK";
    }
    
    @GetMapping("/sendAndConfirm")
    public Object sendAndConfirm(){
        produceService.sendAndConfirm();
        return "OK";
    }
}
