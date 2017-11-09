package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.service.UserService.UserService;
import com.geekluxun.greateapp.spring.SpringDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by luxun on 2017/11/4.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDemoTest {

    //此处必须使用qualifier限定某一个bean，因为有好几个类型是SpringDemo的bean
    @Autowired
    @Qualifier("springDemo1")
    SpringDemo springDemo;

    //@Resource //没有显示名字就类似@Autowired 是根据bean类型 选择要注入的bean
    @Resource(name = "springDemo2") // 寻找一个名字叫springDemo2的bean 注入
    SpringDemo springDemo2;

    @Resource(name = "springDemoAlias") // 寻找一个名字叫springDemoAlias的bean 注入 ,name是bean的别名
    SpringDemo springDemoAlias;

    //根据bean的类型 选择bean注入
    @Autowired
    @Qualifier("springDemo3")
    SpringDemo springDemo3;


    @Resource(name = "springDemo4")
    SpringDemo springDemo4;



    @Resource(name = "springDemo5")
    SpringDemo springDemo5;


    @Resource(name = "springDemo6")
    SpringDemo springDemo6;



    @Resource(name = "springDemo7")
    SpringDemo springDemo7;


    @Resource(name = "springDemo8")
    SpringDemo springDemo8;

    @Resource(name = "springDemo9")
    SpringDemo springDemo9;

    @Resource(name = "springDemo10")
    SpringDemo springDemo10;


    @Resource(name = "springDemo11")
    SpringDemo springDemo11;



    @Resource(name = "springDemoConfig")
    SpringDemo springDemo13;



    @Resource(name = "userServiceConfig")
    UserService userService;


    @Resource(name = "userServiceConfig")
    UserService userService2;


//    @Autowired
//    MockMvc mockMvc;


    @Test
    public void test1(){
        System.out.print("测试springDemo");
    }

    @Test
    public void test(){
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
//        UserService userService2 = (UserService) ctx.getBean("userServiceConfig2");
//        UserService userService = (UserService) ctx.getBean("userServiceConfig");
//        System.out.println();
    }

}
