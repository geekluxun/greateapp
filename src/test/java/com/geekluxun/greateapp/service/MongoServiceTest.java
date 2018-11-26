package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.entity.TUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by luxun on 2017/10/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoServiceTest {
    @Autowired
    @Qualifier(value = "mongoServiceImpl")
    MongoService mongoService;

    @Before
    public void init() {

    }


    @Test
    public void testSave() {
        TUser user = new TUser();
        user.setId(1L);
        user.setName("lx");
        user.setPassword("112233");
        mongoService.saveUser(user);
    }

}
