package com.geekluxun.springdata.jpa.entity.user;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-04 16:32
 * @Description:
 * @Other:
 */
@Entity
@Data
@Slf4j
@EntityListeners({AuditingEntityListener.class,})
@NamedQuery(name = "User.findEmail",
        query = "select u from User u where u.emailAddress = ?1")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;


    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @CreatedDate
    private Date createTime;
    /**
     * 创建人
     */
    @Column(name = "create_by")
    @CreatedBy
    private String createBy;
    /**
     * 修改时间
     */
    @Column(name = "lastmodified_time")
    @LastModifiedDate
    private Date lastmodifiedTime;
    /**
     * 修改人
     */
    @Column(name = "lastmodified_by")
    @LastModifiedBy
    private String lastmodifiedBy;

    /**
     * 邮件地址
     */
    @Column(name = "emailAddress")
    private String emailAddress;

    public User() {
    }

    /**
     * 每次save方法调用后都会触发
     *
     * @return
     */
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
