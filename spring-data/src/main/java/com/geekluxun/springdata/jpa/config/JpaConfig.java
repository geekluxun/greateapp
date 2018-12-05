package com.geekluxun.springdata.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-04 16:32
 * @Description: JPA资源库定义
 * @Other:
 */
@Configuration
// 必须加上basePackage，让spring去扫描所有JpaRepository接口来实例化所有Repository
@EnableJpaRepositories(basePackages = "com.geekluxun.springdata.jpa")
@EnableJpaAuditing(auditorAwareRef = "userSecurityAuditorAware")
public class JpaConfig {

//    @Bean 
//    public EntityManagerFactory entityManagerFactory() {
//        return null;
//    }
}


