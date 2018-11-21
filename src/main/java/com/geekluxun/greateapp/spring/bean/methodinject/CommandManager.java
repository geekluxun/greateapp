package com.geekluxun.greateapp.spring.bean.methodinject;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-21 10:30
 * @Description:  spring 方法注入示例
 * 方法注入的使用场景主要是在一个单例bean A 注入一个prototype的bean B ，但是因为单例A只会注入一次B,
 * 导致每次调用B的方法时候都是一个实例，通过方法注入这个手段，就可以实现在单例A（CommandManager）每次调用B（Command）都会
 * 产生一个新的Command实例
 * @Other:
 */
@Component
public abstract class CommandManager {

    @Autowired
    private ApplicationContext applicationContext;

//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;    
//    }
    
    public Object process() {
        Command command = createCommand();
        return command.execute();
    }

    /**
     * 这是一个抽象的查找方法，没有具体实现
     * SPRING通过CGLIB动态代理 查找名字叫"command"的bean，
     * 动态的实现此抽象方法
     */
    @Lookup(value = "command")
    protected abstract Command createCommand();
}    

//    @Lookup
//    protected  Command createCommand(){
//        return this.applicationContext.getBean("command", Command.class);
//    }
//}

