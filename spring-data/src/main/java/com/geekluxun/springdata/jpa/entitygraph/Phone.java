package com.geekluxun.springdata.jpa.entitygraph;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: luxun
 * @Create: 2018-12-09 13:05
 * @Description:
 * @Other:
 */
@Entity
public class Phone {
    @Id
    @GeneratedValue
    private int id;
    private String number;
    private String type;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
