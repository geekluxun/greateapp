package com.geekluxun.messagemiddleware.rabbitmq.transaction.producer;

import com.geekluxun.messagemiddleware.rabbitmq.demo.ProducerDemoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-16 15:16
 * @Description:
 * @Other:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerDemoServiceTest {

    @Autowired
    private ProducerDemoService produceService;
    
    
    @Test
    public void send() {
        produceService.sendAndReceive();
        Assert.assertTrue(true);
    }
}