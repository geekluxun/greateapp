package com.geekluxun.greateapp.spring;

import com.geekluxun.greateapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by luxun on 2017/11/4.
 */

public class SpringDemo {


    private UserService userService;

    private Integer age;

    private String name ;

    private static SpringDemo springDemo = new SpringDemo();

    public SpringDemo(){

    }

    public SpringDemo(Integer age , String name){
        this.age = age;
        this.name = name;
    }

    public static SpringDemo createInstance(){
        return springDemo;
    }


    public  SpringDemo createInstance2(){
        return springDemo;
    }

    //表示此属性必须在实例化时指定
    @Required
    public void setUserService(@Autowired UserService userService){
        this.userService = userService;
    }

}
