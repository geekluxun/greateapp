package com.geekluxun.greateapp.spring.bean.annotation;

import com.geekluxun.greateapp.BaseTest;
import com.geekluxun.greateapp.dto.PersonDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-22 11:47
 * @Description:
 * @Other:
 */
public class AutowireExampleTest extends BaseTest {
    @Autowired
    AutowireExample example;

    @Test
    public void test1() {
        example.getUserService().isSucceed();
        example.getValidatorService().testValidate(new PersonDto());
        Assert.assertTrue(true);
    }

    @Test
    public void test2() {
        int size = example.getBaseAnnotationFoos().size();

        example.getBaseAnnotationFoos().stream().forEach((a -> System.out.println(a.getOrder())));

        Assert.assertTrue(true);
    }

    @Test
    public void test3() {
        String order = example.getBaseAnnotationFoo().getOrder();
        Assert.assertTrue(true);
    }

}