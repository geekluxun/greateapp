package com.geekluxun.greateapp.spring.bean;

import com.geekluxun.greateapp.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-20 16:41
 * @Description:
 * @Other:
 */

public class BeanDemoTest extends BaseTest{
    
    @Autowired
    BeanDemo beanDemo;
    
    @Test
    public void demo1() {
        beanDemo.demo1();
        System.out.println("dd");
    }
}