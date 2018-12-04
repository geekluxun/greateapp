package com.geekluxun.greateapp.spring.bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-22 16:15
 * @Description: spring自动扫描注解来装配bean
 * 加了过滤条件
 * @Other:
 */
@Configuration
@ComponentScan(basePackages = "com.geekluxun",
        includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Stub.*Repository"),
        excludeFilters = @ComponentScan.Filter(Repository.class))
public class AppConfig {

}