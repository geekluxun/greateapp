
package com.geekluxun.greateapp.spring.jpa.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/30 13:43
 * Description:
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 注意By后的必须是User的一个属性
     * @param name
     * @return
     */
    List<User> findByName(String name);

    User findByNameAndAge(String name, Integer age);

    /**
     * 自定义查询语句
     * @param name
     * @return
     */
    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);
    
}
