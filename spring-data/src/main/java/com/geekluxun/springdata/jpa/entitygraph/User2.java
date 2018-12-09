package com.geekluxun.springdata.jpa.entitygraph;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: luxun
 * @Create: 2018-12-09 13:05
 * @Description:
 * @Other:
 */
@Entity
@NamedEntityGraph(name = "user-phones-entity-graph", attributeNodes = @NamedAttributeNode("phones"))
public class User2 {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Address> addresses;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Phone> phones;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(String number, String type) {
        if (phones == null) {
            phones = new HashSet<>();
        }
        Phone p = new Phone();
        p.setNumber(number);
        p.setType(type);
        phones.add(p);
    }

    public void addAddress(String street, String city, String country) {
        if (addresses == null) {
            addresses = new HashSet<>();
        }
        Address a = new Address();
        a.setStreet(street);
        a.setCity(city);
        a.setCountry(country);
        addresses.add(a);
    }
}
