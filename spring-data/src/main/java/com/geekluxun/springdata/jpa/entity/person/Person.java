package com.geekluxun.springdata.jpa.entity.person;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-05 11:18
 * @Description:
 * @Other:
 */
@Data
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private Integer age;

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Person() {
    }
}
