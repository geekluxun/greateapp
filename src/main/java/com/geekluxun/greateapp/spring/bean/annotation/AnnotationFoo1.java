package com.geekluxun.greateapp.spring.bean.annotation;

import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-22 14:39
 * @Description: 注意 Order注解影响bean的注入顺序，但不影响单例startup顺序
 * @Other:
 */
@Component
@Order(value = 2)
@DependsOn
@MyQualifier(dateFormat = "yyyyHHmm")
public class AnnotationFoo1 implements BaseAnnotationFoo {
    @Override
    public String getOrder() {
        return "我是AnnotationFoo1";
    }
}
