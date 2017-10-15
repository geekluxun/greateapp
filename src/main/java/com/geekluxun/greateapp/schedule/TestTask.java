package com.geekluxun.greateapp.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luxun on 2017/9/9.
 */
public class TestTask {
    private final Logger logger = LoggerFactory.getLogger(TestTask.class);

    public void execute(){
        //System.out.println("test task!!!");
        logger.info("============== test task! ============");
    }
}
