package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.BaseTest;
import com.geekluxun.greateapp.cache.CacheTestService;
import com.geekluxun.greateapp.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by luxun on 2017/11/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTest extends BaseTest {

    @Autowired
    CacheTestService cacheTestService;

    @Test
    public void test() {
        UserDto dto = new UserDto();
        dto.setPassword("1122");
        dto.setName("luxun2");
        cacheTestService.getUser(dto);
    }


}
