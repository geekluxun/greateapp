package com.geekluxun.messagemiddleware.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2019-01-17 14:04
 * @Description:
 * @Other:
 */
public class IdGenerateUtil {

    public static String genId(String prefix) {
        String id = prefix + System.currentTimeMillis() + RandomStringUtils.randomNumeric(10);
        return id;
    }
}
