package com.geekluxun.greateapp.spring;

import com.geekluxun.greateapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by luxun on 2017/11/4.
 */

public class SpringDemo {


    private UserService userService;

    private Integer age;

    private String name ;

    private Properties myProperties;

    private List myList;

    private Map myMap;

    private Set mySet;

    private static SpringDemo springDemo = new SpringDemo();


    public SpringDemo(){

    }

    public SpringDemo(Integer age , String name){
        this.age = age;
        this.name = name;
    }

    public static SpringDemo createInstance(){
        return springDemo;
    }

    public  SpringDemo createInstance2(){
        return springDemo;
    }

    public static SpringDemo createInstance3(Integer age, String name){
        SpringDemo springDemo = new SpringDemo(age, name);
        return springDemo;
    }


    //表示此属性必须在实例化时指定
    @Required
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    public Properties getMyProperties() {
        return myProperties;
    }

    public void setMyProperties(Properties myProperties) {
        this.myProperties = myProperties;
    }

    public List getMyList() {
        return myList;
    }

    public void setMyList(List myList) {
        this.myList = myList;
    }

    public Map getMyMap() {
        return myMap;
    }

    public void setMyMap(Map myMap) {
        this.myMap = myMap;
    }

    public Set getMySet() {
        return mySet;
    }

    public void setMySet(Set mySet) {
        this.mySet = mySet;
    }

    public void init(){

    }

    public UserService getUserService() {
        return userService;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
