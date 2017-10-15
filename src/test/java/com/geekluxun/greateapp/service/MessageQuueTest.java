package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.mq.producer.TestQueueMessageProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by luxun on 2017/10/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageQuueTest {

    @Autowired
    TestQueueMessageProducer messageProducer;

    @Test
    public void testSendMegQueue(){
        messageProducer.sendMsg("luxun");
    }
}


