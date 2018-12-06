package com.geekluxun.springdata.jpa.entity.base;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-05 14:55
 * @Description: 自定义基库
 * @Other:
 */
class MyRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> {

    private final EntityManager entityManager;

    MyRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        // Keep the EntityManager around to used from the newly introduced methods.
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public <S extends T> S save(S entity) {
        return null;
    }
}