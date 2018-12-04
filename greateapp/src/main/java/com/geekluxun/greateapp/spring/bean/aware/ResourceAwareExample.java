package com.geekluxun.greateapp.spring.bean.aware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-22 9:53
 * @Description:
 * @Other:
 */
@Component
public class ResourceAwareExample implements ResourceLoaderAware {

    @Autowired
    ApplicationContext context;

    private ResourceLoader loader;

    /**
     * 这个方法会被容器回调，这样我们的bean就可以持有ResourceLoader引用来操作资源
     *
     * @param resourceLoader
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.loader = resourceLoader;
    }

    public void loadDemo() {
        // 需要指名classpath:，否则无法获取资源
        Resource resource = context.getResource("classpath:spring/spring-quartz.xml");
        try {
            URL url = resource.getURL();
            System.out.println("资源URL:" + url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
