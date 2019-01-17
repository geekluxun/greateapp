package com.geekluxun.messagemiddleware.rabbitmq.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-16 14:55
 * @Description:
 * @Other:
 */
@Service
@Slf4j
public class ProducerDemoService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    private static final String QUEUE = "myqueue2";


    //@PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback");
            }
        });

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("ReturnCallback");
            }
        });
    }

    /**
     * 发送消息并等待响应从消费方
     */
    public void sendAndReceive() {
        String response = (String) rabbitTemplate.convertSendAndReceive("myqueue2", "hello!!!");
        log.info("收到响应:" + response);
    }


    /**
     *
     */
    public void sendAndConfirm() {
        CorrelationData data = new CorrelationData();
        Message message = MessageBuilder.withBody("hello!!!".getBytes()).build();
        rabbitTemplate.send("default", QUEUE, message, data);
    }


    public void sendAndMsgPostprocessor() {
        rabbitTemplate.convertAndSend(QUEUE, (Object) "hello222", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setMessageId("123444");
                return message;
            }
        });

    }
}
