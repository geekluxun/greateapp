package com.geekluxun.greateapp.spring.bean.annotation;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-22 14:39
 * @Description:
 * @Other:
 */
@Component
@Order(value = 1)
@MyQualifier(dateFormat = "yyyy")
public class AnnotationFoo2 implements BaseAnnotationFoo {
    @Override
    public String getOrder() {
        return "我是AnnotationFoo2";
    }
}
