package com.geekluxun.springdata.jpa.entity.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
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
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            User user = new User();
            if (i == 20) {
                user.setName("geek");
            } else {
                user.setName("luxun");
            }
            user.setAge(i + 1);
            users.add(user);
        }
        userRepository.saveAll(users);
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

    /**
     * 分页查询
     */
    @Test
    public void findByPage() {


        Page<User> userPage = userRepository.findAll(PageRequest.of(0, 10));
        Assert.assertTrue(userPage.getTotalElements() == 21);
        userPage = userRepository.findAll(PageRequest.of(1, 10));
        Assert.assertTrue(userPage.getTotalPages() == 3);
        userPage = userRepository.findAll(PageRequest.of(2, 10));
        List<User> users1 = userPage.getContent();
        Assert.assertTrue(users1.get(0).getAge() == 21);
    }

    /**
     * 结果流
     */
    @Test
    public void findAllByCustomQueryAndStream() {
        try (Stream<User> userStream = userRepository.findAllByCustomQueryAndStream()) {
            userStream.forEach((a) -> System.out.println("userId:" + a.getId()));
        }
    }

    /**
     * 异步查询
     */
    @Test
    public void findOneByName() throws Exception {
        CompletableFuture<User> future = userRepository.findOneByName("geek");
        User user = future.get();
        Assert.assertTrue(user.getName().equals("geek"));
    }

    /**
     * 自定义资源库方法
     */
    @Test
    public void customizeMethod() {
        User user = new User();
        user.setName("ddd");
        userRepository.mySave(user);
        Assert.assertTrue(true);
    }

    @Test
    public void findAndSort() {
        List<User> users = userRepository.findByAndSort("luxun", Sort.by("age").descending());
        Assert.assertTrue(users.get(0).getAge() == 20);
    }
}