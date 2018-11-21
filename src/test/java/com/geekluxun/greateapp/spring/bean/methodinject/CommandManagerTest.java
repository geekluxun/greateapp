package com.geekluxun.greateapp.spring.bean.methodinject;

import com.geekluxun.greateapp.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-21 13:47
 * @Description:
 * @Other:
 */
public class CommandManagerTest extends BaseTest {
    @Autowired
    CommandManager commandManager;

    @Test
    public void process() {
        commandManager.process();
        commandManager.process();
        System.out.println();
    }
}