package com.geekluxun.greateapp;

import com.geekluxun.greateapp.controller.MainController;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by luxun on 2017/11/14.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {
    public static final Logger logger = LoggerFactory.getLogger(MainController.class);

}
