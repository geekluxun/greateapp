package com.geekluxun.greateapp.spring.springmvc.view.excelview;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * @Author: luxun
 * @Create: 2018-12-02 10:54
 * @Description:
 * @Other:
 */
public class ExcelViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {

        return new ExcelView();
    }
}
