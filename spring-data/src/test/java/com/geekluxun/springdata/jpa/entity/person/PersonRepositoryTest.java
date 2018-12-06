package com.geekluxun.springdata.jpa.entity.person;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-05 11:26
 * @Description:
 * @Other:
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {
    @Autowired
    PersonRepository personRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findDistinctPeopleByLastnameOrFirstname() {
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setFirstname("lu");
            person.setLastname(RandomStringUtils.randomAlphanumeric(5));
            personList.add(person);
        }

        personRepository.saveAll(personList);

        List<Person> result = personRepository.findDistinctPeopleByLastnameOrFirstname(null, "lu");
        Assert.assertTrue(result.size() == 10);
        result = personRepository.findPeopleDistinctByLastnameOrFirstname(null, "lu");
        Assert.assertTrue(result.size() == 10);
    }

    @Test
    public void findPeopleDistinctByLastnameOrFirstname() {
    }

    @Test
    public void findByLastnameIgnoreCase() {
        List<Person> personList = Lists.newArrayList(new Person("lu", "xun"), new Person("lu", "XUN"));
        personRepository.saveAll(personList);
        List<Person> resultSet = personRepository.findByLastnameIgnoreCase("xun");
        Assert.assertTrue(resultSet.size() == 2);
    }

    @Test
    public void findByLastnameAndFirstnameAllIgnoreCase() {
    }

    @Test
    public void findByLastnameOrderByFirstnameAsc() {
    }

    @Test
    public void findByLastnameOrderByFirstnameDesc() {
    }

    @Test
    public void findTop3ByLastname() {

        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setFirstname(RandomStringUtils.randomAlphanumeric(5));
            person.setLastname("xun");
            personList.add(person);
        }

        personRepository.saveAll(personList);

        Slice<Person> personSlice = personRepository.findTop3ByLastname("xun", PageRequest.of(0, 3));

        Assert.assertTrue(personSlice.getSize() == 3);
    }

    @Test
    public void findFirst10ByLastname() {

        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Person person = new Person();
            person.setFirstname(RandomStringUtils.randomAlphanumeric(5));
            person.setLastname("xun");
            personList.add(person);
        }

        personRepository.saveAll(personList);
        // 按照firtname降序
        List<Person> personList2 = personRepository.findFirst10ByLastname("xun", Sort.by("firstname").descending());
        Assert.assertTrue(personList2.size() == 10);
    }
}