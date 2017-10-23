package com.geekluxun.greateapp.kafka.producer;

import kafka.Kafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by luxun on 2017/10/20.
 */
@Component
public class Producer {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    MessageChannel channel;

    @Value("${kafka.topic.test.1}")
    private String topic;

    public void send(){
        kafkaTemplate.send(topic, "===== message from kafka test.1 ====");
    }

    public void send2(){
        channel.send(MessageBuilder.withPayload("hello!!!").setHeader(KafkaHeaders.TOPIC,topic).build());
    }

}
