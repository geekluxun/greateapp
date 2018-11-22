package com.geekluxun.greateapp.spring.bean.beanpostprocess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-22 10:45
 * @Description: 对所有bean的拦截
 * @Other:
 */
@Component
public class BeanPostProcessorExample implements BeanPostProcessor {

    /**
     * 所有bean在初始化前都会调用
     * @param o
     * @param s
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("======BeanBeforePostProcessor：" +s );
        return o;
    }

    /**
     * 所有bean初始化后都会被调用
     * @param o
     * @param s
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("======BeanAfterPostProcessor：" +s );
        return o;
    }
}
