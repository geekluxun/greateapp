package com.geekluxun.messagemiddleware.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.geekluxun.messagemiddleware.constant.CommonConstants.RABBITMQ_QUEUE;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue queue() {
        return new Queue(RABBITMQ_QUEUE, true);
    }
}    

