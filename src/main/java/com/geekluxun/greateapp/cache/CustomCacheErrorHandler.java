package com.geekluxun.greateapp.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

/**
 * Created by luxun on 2017/11/17.
 */

public class CustomCacheErrorHandler implements CacheErrorHandler {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CustomCacheErrorHandler.class);

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        logger.error("获取缓存数据失败", exception);
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        logger.error("放入缓存数据失败", exception);
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        logger.error("清除缓存数据失败", exception);
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        logger.error("清空缓存数据失败", exception);
    }

}

