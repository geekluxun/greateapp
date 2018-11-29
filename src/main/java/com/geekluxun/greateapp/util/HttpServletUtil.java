package com.geekluxun.greateapp.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author: luxun
 * @Create: 2018-11-28 20:54
 * @Description:
 * @Other:
 */
public class HttpServletUtil {

    public static void printHttpHeaders(HttpServletRequest request) {
        System.out.println("请求URL:" + request.getRequestURL() + "的所有请求头:");
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String headerName = names.nextElement();
            System.out.println("[headerName]:" + headerName + "[headerValue]:" + request.getHeader(headerName));
        }
    }
}
