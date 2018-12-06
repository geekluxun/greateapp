package com.geekluxun.springdata.jpa.entity.person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-05 11:19
 * @Description:
 * @Other:
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * Enables the distinct flag for the query
     *
     * @param lastname
     * @param firstname
     * @return
     */
    List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);

    List<Person> findPeopleDistinctByLastnameOrFirstname(String lastname, String firstname);


    /**
     * Enabling ignoring case for an individual property
     *
     * @param lastname
     * @return
     */
    List<Person> findByLastnameIgnoreCase(String lastname);

    /**
     * Enabling ignoring case for all suitable properties
     *
     * @param lastname
     * @param firstname
     * @return
     */
    List<Person> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);

    /**
     * Enabling static ORDER BY for a query
     *
     * @param lastname
     * @return
     */
    List<Person> findByLastnameOrderByFirstnameAsc(String lastname);

    List<Person> findByLastnameOrderByFirstnameDesc(String lastname);


    /**
     * 结果集第1个
     *
     * @return
     */
    Person findFirstByOrderByLastnameAsc();

    /**
     * 结果集第1个
     *
     * @return
     */
    Person findTopByOrderByAgeDesc();

    /**
     * 结果集前10个
     *
     * @return
     */
    Page<Person> queryFirst10ByLastname(String lastname, Pageable pageable);

    /**
     * 结果集前3个
     *
     * @param lastname
     * @param pageable
     * @return
     */
    Slice<Person> findTop3ByLastname(String lastname, Pageable pageable);

    List<Person> findFirst10ByLastname(String lastname, Sort sort);

    List<Person> findTop10ByLastname(String lastname, Pageable pageable);
}
