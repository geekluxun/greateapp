package com.geekluxun.greateapp.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.core.env.PropertySource;

import java.io.IOException;

/**
 * Created by luxun on 2017/11/20.
 */
@Configuration
@EnableConfigurationProperties(FooProperties.class)
public class YmalConfig {

}