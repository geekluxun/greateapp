package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.entity.TUser;
import com.geekluxun.greateapp.schedule.TestTask;
import com.geekluxun.greateapp.service.UserService.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by luxun on 2017/9/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    @Qualifier("userService33")
    UserService userService;

    @Autowired
    TestTask testTask;

    @Test
    public void testAddUser() {
        TUser user = new TUser();
        user.setName("luxun1234");
        user.setPassword("1234");
        try {
            userService.addUser(user);
        } catch (Exception e) {

        }
    }

    @Test
    public void testScheduleTask() {
        testTask.execute();
    }
}
