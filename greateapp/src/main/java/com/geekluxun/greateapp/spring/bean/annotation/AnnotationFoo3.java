package com.geekluxun.greateapp.spring.bean.annotation;

import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-22 14:39
 * @Description:
 * @Other:
 */
@Component
@Order(value = 3)
@DependsOn({"annotationFoo1"})
@MyQualifier(dateFormat = "MM")
public class AnnotationFoo3 implements BaseAnnotationFoo {

    @Override
    public String getOrder() {
        return "我是AnnotationFoo3";
    }
}
