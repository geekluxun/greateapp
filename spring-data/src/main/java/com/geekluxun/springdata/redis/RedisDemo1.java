package com.geekluxun.springdata.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-02-20 14:57
 * @Description:
 * @Other:
 */
@Service
@Slf4j
public class RedisDemo1 {
    
    @Autowired
    @Qualifier(value = "redisTemplate")        
    RedisTemplate template;

    /**
     * 字符串测试 
     * @param key
     * @param value
     */
    public void stringTest(String key, String value ) {
        template.opsForValue().set(key, value);
    }

    /**
     * hash测试
     */
    public void hashTest() {
        template.opsForHash().put("token", "userid-1", "111");
        template.opsForHash().put("token", "userid-2", "222");
        template.opsForHash().put("token", "userid-3", "333");

        String userId2Token = (String) template.opsForHash().get("token", "userid-2");
        log.info("用户2token值:" + userId2Token);
    }

    /**
     * list测试
     */
    public void listTest(){
        template.opsForList().leftPush("list1", "111");
        template.opsForList().leftPush("list1", "222");
    }

    /**
     * set测试
     */
    public void setTest(){
        template.opsForSet().add("set1", "111");
        template.opsForSet().add("set1", "222", "333");
    }

    /**
     * zset测试
     */
    public void zSetTest(){
        template.opsForZSet().add("zset1", "111", 10);
        template.opsForZSet().add("zset1", "222", 12);
        template.opsForZSet().add("zset1", "333", 15);
        
        template.opsForZSet().incrementScore("zset1", "111", 10);
        
        long rank = template.opsForZSet().rank("zset1", "333");
        log.info("333的排名" + rank);
    }
}
