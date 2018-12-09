package com.geekluxun.springdata.jpa.entity.base;


import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.Optional;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-05 10:48
 * @Description: 加上@NoRepositoryBean注解，JPA就不会实例化此接口，这个接口的目的是为了继承的，不是用来具体的
 * @Other:
 */
@NoRepositoryBean
interface MyBaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

    Optional<T> findById(ID id);

    <S extends T> S save(S entity);
}
