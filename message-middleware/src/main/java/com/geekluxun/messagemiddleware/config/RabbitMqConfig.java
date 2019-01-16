package com.geekluxun.messagemiddleware.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue queue() {
        return new Queue("myqueue2", true);
    }
}    

