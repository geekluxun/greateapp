package com.geekluxun.springdata.jpa.entity.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-05 14:59
 * @Description: 提供了对DDD的支持，发布领域事件
 * @Other:
 */
@Slf4j
@Component
public class AnAggregateRoot {


    @DomainEvents
    Collection<Object> domainEvents() {
        // … return events you want to get published here
        log.info("======发布领域事件======");
        return null;
    }

    @AfterDomainEventPublication
    void callbackMethod() {
        // … potentially clean up domain events list
        log.info("======领域事件已发布，执行清理已发布领域事件======");
    }
}
