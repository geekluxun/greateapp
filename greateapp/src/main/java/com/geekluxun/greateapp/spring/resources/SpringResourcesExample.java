package com.geekluxun.greateapp.spring.resources;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jms.core.JmsTemplate;

import java.util.List;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/30 10:19
 * Description:
 */

public class SpringResourcesExample {

    public static void main(String[] argc) {
        SpringResourcesExample example = new SpringResourcesExample();
        example.getContextAndResource();
    }


    public void getContextAndResource() {
        /** 必须要加上相对路径spring,classpath 对应编译后生成的target目录结构
         *  上下文加载的时候实例化xml文件中的所有bean
         */

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-quartz.xml");

        /**
         * 通配符实例！！！
         */
        //new ClassPathXmlApplicationContext("classpath*:conf/appContext.xml");
        //new ClassPathXmlApplicationContext(" classpath:com/mycompany/**/service-context.xml");
        //new ClassPathXmlApplicationContext(" classpath:classpath*:META-INF/*-beans.xml");


        //获取bean实例
        context.getBean("testTaskTargetObject");

        //获取资源文件
        Resource resource = context.getResource("spring/spring-quartz.xml");
        System.out.println();
        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());

        SAXReader reader = new SAXReader();
        Document doc = null;

        try {
            // 获取根元素
            doc = reader.read(resource.getFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //xml的根元素
        Element el = doc.getRootElement();
        if (el != null) {
            List list = el.elements();
        }


    }

    public void getFileContext() {
        /**file是和当前操作系统的文件路径有关，可以使用相对路径或者绝对路径*/
        ApplicationContext context2 = new FileSystemXmlApplicationContext("src/main/resources/spring/active-mq.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) context2.getBean("jmsTempate");

        System.out.println();
    }
}
