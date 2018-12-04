package com.geekluxun.greateapp.spring.dataaccess.servcie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-03 11:37
 * @Description:
 * @Other:
 */
@Slf4j
@Component
public class DefaultFooService {

    @Autowired
    @Qualifier(value = "txManager")
    DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * 使用txManager事务管理器
     */
    @Transactional(value = "txManager")
    public String handle() {
        log.info("======DefaultFooService======handle!!!");
        return "ok";
    }
}
