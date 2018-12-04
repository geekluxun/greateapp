package com.geekluxun.springdata.jpa.entity.user;

import com.geekluxun.springdata.SpringDataApplicationTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-04 16:37
 * @Description:
 * @Other:
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void saveAndFind() {
        List<User> users = userRepository.findByName("luxun");
        Assert.assertTrue(users.isEmpty());
        
        User user = new User();
        user.setName("luxun");
        user.setAge(100);
        userRepository.save(user);
        users = userRepository.findByName("luxun");
        System.out.println("user:" + users.get(0));
        Assert.assertTrue(users.size() != 0);
    }
}