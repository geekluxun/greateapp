package com.geekluxun.greateapp.spring.format;

import com.geekluxun.greateapp.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-23 16:02
 * @Description:
 * @Other:
 */
public class FormatExampleTest extends BaseTest {
    @Autowired
    FormatExample formatExample;

    @Test
    public void test1() {
        String moeny = formatExample.getMoney().toString();
        Assert.assertTrue(true);
    }
}