package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.redis.sdr.SpringDataRedisExamples;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by luxun on 2017/10/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataRedisExamplesTest {

    @Autowired
    SpringDataRedisExamples redisExamples;


    @Test
    public void testString(){
        redisExamples.testString();
    }

}
