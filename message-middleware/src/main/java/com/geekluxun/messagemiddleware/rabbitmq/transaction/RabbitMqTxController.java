package com.geekluxun.messagemiddleware.rabbitmq.transaction;

import com.geekluxun.messagemiddleware.rabbitmq.transaction.producer.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-17 15:55
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/rabbitmq/tx")
public class RabbitMqTxController {
    
    @Autowired
    private ProducerService producerService;
    
    
    @GetMapping("/bizHandle")
    public Object bizHandle(){
        producerService.bizHandle();
        return "OK";
    }
}
