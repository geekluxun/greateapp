package com.geekluxun.greateapp.example;


import com.geekluxun.greateapp.spring.SpringDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Date;

/**
 * Created by luxun on 2017/11/21.
 */
public class InjectFileResourceDataExample {

    private static final Logger logger = LoggerFactory.getLogger(InjectFileResourceDataExample.class);

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        ClientBean bean = context.getBean(ClientBean.class);
        bean.doSomething();

    }

    @Configuration
    public static class Config {
        @Bean
        public ClientBean clientBean() {
            return new ClientBean();
        }

        @Bean
        @Qualifier("data1")
        public String myResourceData(@Value("file:D:\\test.txt") Resource myResource) throws IOException {
            File file = myResource.getFile();
            return new String(Files.readAllBytes(file.toPath()));
        }

        @Bean
        @Primary
        @Qualifier("data2")
        public String myResourceData2(@Value("classpath:application.yml") Resource myResource) throws IOException {
            File file = myResource.getFile();
            return new String(Files.readAllBytes(file.toPath()));
        }


        @Bean
        @Qualifier("data3")
        public String myResourceData3(@Value("url:http://www.example.com/") Resource myResource) throws IOException {

            if (myResource.getURL() != null) {
                StringBuilder stringBuilder = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(myResource.getInputStream()))) {

                    reader.lines()
                            .forEach(stringBuilder::append);
                }
                return stringBuilder.toString();
            } else {
                File file = myResource.getFile();
                return new String(Files.readAllBytes(file.toPath()));
            }

        }
    }

    private static class ClientBean {

        @Autowired
        @Qualifier("data3")
        private String myData;

        public void doSomething() throws IOException {
            System.out.println(myData);
        }
    }
}