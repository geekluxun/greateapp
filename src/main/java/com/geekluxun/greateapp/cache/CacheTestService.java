package com.geekluxun.greateapp.cache;

import com.geekluxun.greateapp.dto.UserDto;
import com.geekluxun.greateapp.entity.TUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

import static java.lang.Math.abs;

/**
 * Created by luxun on 2017/11/17.
 */
@Service
public class CacheTestService {
    private static final Logger logger = LoggerFactory.getLogger(CacheTestService.class);

    /**
     * 对于redis缓存，首先会产生一个zset，key是user~keys,value是dto.name的值，
     * 通过value的值是否相同，来决定是从缓存中取还是进入方法中取，
     * 另外还会产生一个key是dto.name，value是方法返回值。
     * @param dto
     * @return
     */
    @Cacheable(value = "user", key = "#dto.name")
    public TUser getUser(UserDto dto){
        logger.info("============ getUser() ========= ");
        TUser user = new TUser();
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setId(abs(new Random().nextLong()));
        return user;
    }
}
