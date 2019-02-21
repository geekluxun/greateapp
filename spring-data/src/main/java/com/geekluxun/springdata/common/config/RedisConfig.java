package com.geekluxun.springdata.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-02-20 14:44
 * @Description:
 * @Other:
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate template = new RedisTemplate();
        // 设置key和value序列化方法 ，解决key和value中存在十六进制数问题!!!
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        // 需要设置默认序列化方式 !!!
        template.setDefaultSerializer(new StringRedisSerializer());
        template.setConnectionFactory(connectionFactory());
        return template;
    }


    /**
     * redis连接工厂
     * @return
     */
    @Bean
    public RedisConnectionFactory connectionFactory() {
        //return new LettuceConnectionFactory(sentinelConfig(), LettuceClientConfiguration.defaultConfiguration());
        return new LettuceConnectionFactory("116.62.63.81", 6379);
    }

    /**
     * redis哨兵模式 配置
     * @return
     */
    @Bean
    public RedisSentinelConfiguration sentinelConfig() {
        // 需要奇数个sentinel，master对应的是这3个哨兵所监控的redis主服务器,具体在sentinel.conf中配置
        RedisSentinelConfiguration config = new RedisSentinelConfiguration();
        config.master("mymaster")
                .sentinel("localhost", 26379)
                .sentinel("localhost", 26380)
                .sentinel("localhost", 26381);
        return config;
    }
}
