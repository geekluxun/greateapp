package com.geekluxun.greateapp.spring.bean.beanpostprocess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-22 10:45
 * @Description: 对所有bean初始化前和后的的回调
 * @Other:
 */
//@Component
public class BeanPostProcessorExample implements BeanPostProcessor {

    /**
     * 所有bean在初始化前都会调用
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("======postProcessBeforeInitialization=====Bean:" + beanName);
        return bean;
    }

    /**
     * 所有bean初始化后都会被调用
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("======postProcessAfterInitialization====== Bean '" + beanName + "' created : " + bean.toString());
        return bean;
    }
}
