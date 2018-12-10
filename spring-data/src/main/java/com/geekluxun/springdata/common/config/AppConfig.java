package com.geekluxun.springdata.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-10 13:54
 * @Description:
 * @Other:
 */
@Configuration
@ImportResource(locations = {"classpath:spring/spring-integration-rmi-client.xml"})
public class AppConfig {
}
