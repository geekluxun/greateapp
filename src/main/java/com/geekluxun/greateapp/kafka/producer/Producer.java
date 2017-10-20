package com.geekluxun.greateapp.kafka.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by luxun on 2017/10/20.
 */
@Component
public class Producer {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.test.1}")
    private String topic;

    public void send(){
        kafkaTemplate.send(topic, "===== message from kafka test.1 ====");
    }
}
