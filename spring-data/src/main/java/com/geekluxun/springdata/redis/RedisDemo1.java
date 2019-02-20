package com.geekluxun.springdata.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-02-20 14:57
 * @Description:
 * @Other:
 */
@Service
public class RedisDemo1 {
    
    @Autowired
    @Qualifier(value = "redisTemplate")        
    RedisTemplate template;
    
    public void test1() {
        template.opsForValue().set("name", "luxun123");
    }
}
