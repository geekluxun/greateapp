package com.geekluxun.messagemiddleware.rabbitmq.transaction.producer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-16 15:16
 * @Description:
 * @Other:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduceServiceTest {

    @Autowired
    private ProduceService produceService;
    
    
    @Test
    public void send() {
        produceService.sendAndReceive();
        Assert.assertTrue(true);
    }
}