package com.geekluxun.greateapp.kafka.producer;

import com.geekluxun.greateapp.controller.MainController;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by luxun on 2017/10/20.
 */
@Service
public class Producer {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(MainController.class);

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    MessageChannel channel;

    @Value("${kafka.topic.test.1}")
    private String topic1;

    @Value("${kafka.topic.test.2}")
    private String topic2;

    public void send(String topic) {
        if (topic.equals("1")) {
            logger.info("========= 开始发送topic1 消息 =========");
            kafkaTemplate.send(topic1, "===== message from kafka topic-1 !!! ====");
        } else if (topic.equals("2")) {
            logger.info("========= 开始发送topic2 消息 =========");

            channel.send(MessageBuilder.withPayload("===== message from kafka topic-2 !!! ====").setHeader(KafkaHeaders.TOPIC, topic2).build());
        } else {
            logger.error("========= 参数错误！！ =========");
        }
    }

}
