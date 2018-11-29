package com.geekluxun.greateapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;
import java.text.SimpleDateFormat;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-23 16:56
 * @Description:
 * @Other:
 */
@Configuration
public class SpringmvcConfig {

    //@Bean
    public MappingJackson2HttpMessageConverter converter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper om = new ObjectMapper();
        om.setDateFormat(new SimpleDateFormat("yyyy"));
        converter.setObjectMapper(om);
        return converter;
    }

    /**
     * 这是一个eTag过滤器
     * @return
     */
    @Bean
    public Filter etag(){
        ShallowEtagHeaderFilter filter = new ShallowEtagHeaderFilter();
        return  filter;
    }
}
