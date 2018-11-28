package com.geekluxun.greateapp.spring.bean.beanpostprocess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-22 11:04
 * @Description:
 * @Other:
 */
@Component
public class FactoryBeanProcessorExample implements BeanFactoryPostProcessor {

    /**
     * 对Bean工厂的拦截
     *
     * @param config
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory config) throws BeansException {
        List<String> beanNames = Arrays.asList(config.getBeanDefinitionNames());
        System.out.println("FactoryBeanProcessorExample管理的beans类名:" + beanNames.size());
        beanNames.stream().forEach(a -> System.out.println(a));
    }
}
