package com.geekluxun.greateapp.redis.sdr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by luxun on 2017/10/29.
 */
public class SpringDataRedisExamples {

    @Autowired
    RedisTemplate redisTemplate;

    public static void main(String argc[]){
        SpringDataRedisExamples examples = new SpringDataRedisExamples();

        examples.testString();
    }


    public void testString(){
        redisTemplate.opsForValue().set("mykey1", "luxun");
    }

}
