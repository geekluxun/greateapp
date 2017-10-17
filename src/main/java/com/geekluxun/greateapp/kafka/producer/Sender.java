package com.geekluxun.greateapp.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by luxun on 2017/10/17.
 */
@Component
public class Sender {

    @Resource
    KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String payload){
        kafkaTemplate.send(topic,payload);
    }
}
