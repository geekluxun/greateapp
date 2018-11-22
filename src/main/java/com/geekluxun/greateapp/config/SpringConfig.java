package com.geekluxun.greateapp.config;

import com.geekluxun.greateapp.service.UserService.UserService;
import com.geekluxun.greateapp.service.UserService.UserServiceImpl;
import com.geekluxun.greateapp.spring.SpringDemo;
import com.geekluxun.greateapp.spring.bean.methodinject.Command;
import com.geekluxun.greateapp.spring.bean.methodinject.ConcreteCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

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
     *
     * @return
     */
    @Bean(initMethod = "init", name = "springDemoConfig")
    public SpringDemo springDemoConfig() {
        SpringDemo springDemo = new SpringDemo();
        springDemo.setUserService(userService);
        springDemo.setAge(111);
        return springDemo;
    }

    /**
     * 接口方式返回实例
     * 注意:
     * 1、这个bean只有在profile是prod且容器调用getBean或者被注入到另一个bean才会实例化（每次实例不同）
     * 2、一个bean可以有多个name，但是name在一个容器中不可以重复
     * @return
     */
    @Bean(name = {"userService", "userService1", "userService2"})
    @Description("描述性")
    @Scope(value = "prototype")
    @Profile(value = "prod")
    public UserService userServiceConfig() {
        return new UserServiceImpl();
    }

    /**
     * 具体类方式返回实例
     *
     * @return
     */
    @Bean(value = "userService222333")
    @Description("描述性")
    @Scope(value = "prototype")
    public UserServiceImpl userServiceConfig2() {
        return new UserServiceImpl();
    }

    @Bean(name = "command")
    @Scope(value = "prototype")
    public Command command() {
        return new ConcreteCommand();
    }

    /**
     * 每次Request请求产生一个新的实例
     * 这里用到了CGLIB代理，把一个小生命周期的bean注入到一个大生命周期的bean(例如单例)需要用到代理
     * 因为单例是在spring容器创建的时候就实例化，此时还没有实例化request级别的bean
     *
     * @return
     */
    @Bean(value = "command2")
    @RequestScope
    public Command commandRequest() {
        return new ConcreteCommand();
    }

    /**
     * 每个HttpSession 产生一个新的实例
     *
     * @return
     */
    @Bean(value = "command3")
    @SessionScope
    public Command commandSession() {
        return new ConcreteCommand();
    }

    /**
     * 每个ServletContext 产生一个新的实例
     * 注意不是ApplicationContext(spring容器) 一个spring容器上下文可以有多个Servlet上下文
     *
     * @return
     */
    @Bean(value = "command4")
    @ApplicationScope
    public Command commandApplication() {
        return new ConcreteCommand();
    }
}    
