package com.geekluxun.greateapp.spring.bean.annotation;

import com.geekluxun.greateapp.service.UserService.UserService;
import com.geekluxun.greateapp.service.ValidatorService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-22 11:46
 * @Description:
 * @Other:
 */
@Component
@Data
public class AutowireExample {

    private UserService userService;
    private ValidatorService validatorService;
    private List<BaseAnnotationFoo> baseAnnotationFoos;

    @Autowired
    @MyQualifier(dateFormat = "MM")
    private BaseAnnotationFoo baseAnnotationFoo;


    public AutowireExample() {

    }

    /**
     * 1、如果只有一个构造方法，可以不用加@Autowired
     * 2、构造函数的两个实参会自动注入
     *
     * @param userService
     * @param validatorService
     */
    @Autowired
    public AutowireExample(@Qualifier("userService33") UserService userService, ValidatorService validatorService) {
        this.userService = userService;
        this.validatorService = validatorService;
    }

    /**
     * 这里注入的bean是一个集合，容器会把所有type是BaseAnnotationFoo的bean的实例都集中起来传给这个构造方法
     *
     * @param baseAnnotationFoos
     */
    @Autowired
    public void setUserServiceSet(List<BaseAnnotationFoo> baseAnnotationFoos) {
        this.baseAnnotationFoos = baseAnnotationFoos;
    }
}
