package com.geekluxun.greateapp.config;

import com.geekluxun.greateapp.service.UserService.UserService;
import com.geekluxun.greateapp.service.UserService.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;

/**
 * Created by luxun on 2017/11/6.
 */

public class SpringDefaultConfig {

    /**
     * 接口方式
     *
     * @return
     */
    @Bean
    @Description("描述性")
    @Scope(value = "prototype")
    public UserService userServiceConfig2() {
        return new UserServiceImpl();
    }
}
