package com.geekluxun.greateapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletContext;
import java.net.MalformedURLException;
import java.net.URL;


@SpringBootConfiguration
@EnableAutoConfiguration
@ImportResource(locations = {"classpath:applicationContext.xml"})
public class GreateappApplication {
    
    
    public static void main(String[] args) {
        GenericWebApplicationContext context = (GenericWebApplicationContext)SpringApplication.run(GreateappApplication.class, args);
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        long maxMemory = Runtime.getRuntime().maxMemory();
        MutablePropertySources sources = context.getEnvironment().getPropertySources();

        System.out.println("activeProfiles:" + activeProfiles[0]);
    }


}
