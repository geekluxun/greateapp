package com.geekluxun.greateapp.config;

import com.geekluxun.greateapp.service.UserService.UserService;
import com.geekluxun.greateapp.service.UserService.UserServiceImpl;
import com.geekluxun.greateapp.spring.SpringDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;

/**
 * Created by luxun on 2017/11/4.
 */
@Configuration
@ComponentScan(basePackages = "com.geekluxun")
@Import({SpringDefaultConfig.class})  //导入另外一个java Config
@ImportResource("classpath:/spring/spring-common.xml") // 导入XML方式配置的Bean
@PropertySource("classpath:application.properties")
@EnableScheduling //使能spring @Schedule注解
@EnableAsync  //使能spring @Async注解
public class SpringConfig {

    /**
     * 注意此方式通过注解@Servcie方式定义的
     */
    @Autowired
    @Qualifier("userService33")
    UserService userService;

    /**
     * 注意此bean是通过XML方式定义的
     */
    @Resource(name = "springDemo10")
    SpringDemo springDemo10;

    /**
     * 实例化这个bean时又用到了另一个UserService，类似xml方式 属性方法！！！
     * @return
     */
    @Bean(initMethod = "init",name = "springDemoConfig")
    public SpringDemo springDemoConfig() {
        SpringDemo springDemo = new SpringDemo();
        springDemo.setUserService(userService);
        springDemo.setAge(111);
        return springDemo;
    }

    /**
     * 接口方式
     * @return
     */
    @Bean
    @Description("描述性")
    @Scope(value = "prototype")
    @Profile(value = "prod")
    public UserService userServiceConfig(){
        return new UserServiceImpl();
    }
}
