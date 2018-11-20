package com.geekluxun.greateapp.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by luxun on 2017/10/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class kafkaTest {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;


    @Before
    public void init() {

    }

    @Test
    public void testSend() {
        kafkaTemplate.send("test1", "luxun!!!");
    }
}
