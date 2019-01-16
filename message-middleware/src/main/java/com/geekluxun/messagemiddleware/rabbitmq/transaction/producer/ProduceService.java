package com.geekluxun.messagemiddleware.rabbitmq.transaction.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-16 14:55
 * @Description:
 * @Other:
 */
@Service
@Slf4j
public class ProduceService {
    @Autowired
    RabbitTemplate rabbitTemplate;
    
    private static final String QUEUE = "myqueue2";
    
    
    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                System.out.println("ConfirmCallback");
            }
        });
        
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println("ReturnCallback");
            }
        });
    }

    /**
     * 发送消息并等待响应从消费方
     */
    public void sendAndReceive() {
        String response= (String) rabbitTemplate.convertSendAndReceive("myqueue2", "hello!!!");
        log.info("收到响应:" + response);  
    }
    
    
    public void sendAndConfirm(){
        CorrelationData data = new CorrelationData();
        data.setId("111333555");
        Message message = MessageBuilder.withBody("hello!!!".getBytes()).setMessageId("333555").build();
        rabbitTemplate.send("default", QUEUE, message, data);
        //rabbitTemplate.convertAndSend(QUEUE, (Object) "hello!!!", data);
    }
}
