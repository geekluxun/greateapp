package com.geekluxun.greateapp.cache;

import com.geekluxun.greateapp.dto.UserDto;
import com.geekluxun.greateapp.entity.TUser;
import com.geekluxun.greateapp.service.UserService.UserService;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.Random;

import static java.lang.Math.abs;

/**
 * Created by luxun on 2017/11/17.
 * 代码示例: https://github.com/zhangkaitao/spring4-showcase/tree/master/spring-cache
 */
@Service
public class CacheTestService {


    private static final Logger logger = LoggerFactory.getLogger(CacheTestService.class);

    /**
     * 对于redis缓存，首先会产生一个zset，key是user~keys,value是dto.name的值，
     * 通过value的值是否相同，来决定是从缓存中取还是进入方法中取，
     * 另外还会产生一个key是dto.name，value是方法返回值。
     *
     * @param dto
     * @return
     */
    @Cacheable(value = "user", key = "#dto.name")
    public TUser getUser(UserDto dto) {
        logger.info("============ getUser() ========= ");
        TUser user = new TUser();
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setId(abs(new Random().nextLong()));
        return user;
    }

    /**
     * 会把方法的返回值TUser缓存,一般用在update或add数据库操作上
     * conditon表示条件缓存，满足提交的才会缓存
     *
     * @param dto
     * @return
     */
    @CachePut(value = "user", key = "#dto.name", condition = "#result.username ne 'zhang'")
    public TUser saveUser(UserDto dto) {
        logger.info("============ saveUser() ========= ");
        TUser user = new TUser();
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setId(abs(new Random().nextLong()));
        return user;
    }


    /**
     * 会删除缓存, 从缓存中移除指定key的数据
     *
     * @param dto
     * @return
     */
    @CacheEvict(value = "user", key = "#dto.name")
    public TUser deleteUser(UserDto dto) {
        logger.info("============ deleteUser() ========= ");
        TUser user = new TUser();
        return user;
    }

    /**
     * 移除所有数据
     */
    @CacheEvict(value = "user", allEntries = true)
    public void deleteAll() {

    }


    /**
     * 组合cache示例
     *
     * @param user
     * @return
     */
    @Caching(
            put = {@CachePut(value = "user", key = "#user.id"),
                    @CachePut(value = "user", key = "#user.username"),
                    @CachePut(value = "user", key = "#user.email")}
    )
    public TUser save(User user) {

        return new TUser();
    }
}