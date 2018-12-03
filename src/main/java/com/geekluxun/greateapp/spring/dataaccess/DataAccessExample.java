package com.geekluxun.greateapp.spring.dataaccess;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-03 10:16
 * @Description:
 * @Other:
 */
public class DataAccessExample {


    public void demo1() {
        // JDBC 帮助类
        DataSourceUtils.getConnection(null);
        // JPA 帮助类
        EntityManagerFactoryUtils.getTransactionalEntityManager(null);
        // Hibernate 帮助类
        SessionFactoryUtils.getDataSource(null);
    }


    /**
     * 使用的事务管理器名字叫tx1
     * 系统默认的事务管理器名字叫:transactionManager
     */
    @Transactional(value = "tx1")
    public void tx() {

    }

    /**
     * 使用的另一个事务管理器名字叫tx2
     */
    @Transactional(value = "tx2")
    public void tx2() {

    }

    /**
     * 事务处理中各个阶段的监听
     */
    @TransactionalEventListener
    public void handleOrderCreatedEvent() {

    }

}
