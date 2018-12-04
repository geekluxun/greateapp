package com.geekluxun.greateapp.spring.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-22 11:26
 * @Description: 自定义beanFactory实现自己一下bean创建逻辑或者一些复杂的init逻辑
 * 这个类没有真正掌握其使用场景和方法!!!
 * @Other:
 */
@Component
public class FactoryBeanExmaple implements FactoryBean<Object> {
    @Override
    public Object getObject() throws Exception {
        return new Object();
    }

    @Override
    public Class<?> getObjectType() {
        return Object.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
