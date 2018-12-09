package com.geekluxun.springdata.jpa.entitygraph;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: luxun
 * @Create: 2018-12-09 13:04
 * @Description:
 * @Other:
 */
@Entity
public class Address {
    @Id
    @GeneratedValue
    private long id;
    private String street;
    private String city;
    private String country;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}