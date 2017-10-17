package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.kafka.producer.Sender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by luxun on 2017/10/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class kafkaTest {

    @Autowired
    Sender sender;

    @Value("${kafka.topic.test.1}")
    String topic;

    @Before
    public void init(){

    }

    @Test
    public void testSend(){
        sender.send(topic,"hello!!!");
    }
}
