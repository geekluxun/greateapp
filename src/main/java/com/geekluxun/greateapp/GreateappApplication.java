package com.geekluxun.greateapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;


@SpringBootConfiguration
@EnableAutoConfiguration
@ImportResource(locations={"classpath:applicationContext.xml"})
public class GreateappApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreateappApplication.class, args);
	}



}
