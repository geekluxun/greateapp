package com.geekluxun.messagemiddleware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.geekluxun.messagemiddleware.dao"})
public class MessageMiddlewareApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageMiddlewareApplication.class, args);
    }

}

