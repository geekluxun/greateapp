package com.geekluxun.greateapp.redis.sdr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by luxun on 2017/10/29.
 */
@Service
public class SpringDataRedisExamples {
    private static final Logger logger = LoggerFactory.getLogger(SpringDataRedisExamples.class);


    @Autowired
    private RedisTemplate redisTemplate;

    private String testKey1 = "mykey1";

    public void testString(){

        try {
            redisTemplate.opsForValue().set(testKey1, "luxun");

            if (redisTemplate.hasKey(testKey1)){
                String value = (String) redisTemplate.opsForValue().get(testKey1);
                logger.info("================ 读取redis " + testKey1 + "的值为:" + value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
