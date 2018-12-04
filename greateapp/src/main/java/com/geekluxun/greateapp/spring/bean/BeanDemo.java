package com.geekluxun.greateapp.spring.bean;

import com.geekluxun.greateapp.spring.SpringDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-20 16:52
 * @Description:
 * @Other:
 */
@Component
public class BeanDemo {
    @Autowired
    ApplicationContext context;

    public void demo1() {
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-common.xml");
        SpringDemo springDemo = (SpringDemo) context.getBean("springDemoAlias");
        SpringDemo springDemo2 = (SpringDemo) context.getBean("springDemo2ForAlians1");

        SpringDemo springDemo3 = (SpringDemo) context.getBean("springDemo2ForAlians2");

        springDemo.getAge();
        System.out.println("");
    }
}
