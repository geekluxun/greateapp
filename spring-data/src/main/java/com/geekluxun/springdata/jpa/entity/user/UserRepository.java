
package com.geekluxun.springdata.jpa.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-04 16:32
 * @Description:
 * @Other:
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 注意By后的必须是User的一个属性
     * @param name
     * @return
     */
    List<User> findByName(String name);

    List<User> findByNameAndAge(String name, Integer age);

    /**
     * 自定义查询语句
     * @param name
     * @return
     */
    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);
    
}
