package com.geekluxun.greateapp.spring.bean.methodinject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-21 13:41
 * @Description:
 * @Other:
 */

public class ConcreteCommand implements Command {
    
    @Override
    public Object execute() {
        System.out.println("this command:" + this);
        return null;
    }
}