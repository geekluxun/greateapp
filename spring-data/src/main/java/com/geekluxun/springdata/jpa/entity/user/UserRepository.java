package com.geekluxun.springdata.jpa.entity.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.util.concurrent.ListenableFuture;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static org.hibernate.jpa.QueryHints.HINT_COMMENT;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-04 16:32
 * @Description:
 * @Other:
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomizedUserRepository {

    /**
     * 1、注意By后的必须是User的一个属性
     * 2、这里面使用了查询提示功能，查询提示是Hibenate预定义好的很多
     *
     * @param name
     * @return
     */
    @QueryHints(value = {@QueryHint(name = HINT_COMMENT, value = "a query for pageable"), @QueryHint(name = HINT_COMMENT, value = "a query for pageable")},
            forCounting = false)
    List<User> findByName(@NonNull String name);

    @Nullable
    List<User> findByNameAndAge(String name, Integer age);

    /**
     * 自定义查询语句
     *
     * @param name
     * @return
     */
    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

    /**
     * JDK8中的流的支持
     *
     * @return
     */
    @Query("select u from User u")
    Stream<User> findAllByCustomQueryAndStream();

    /**
     * 异步查询（真正的查询任务使用TaskExecutor在另一个线程执行）
     *
     * @param name
     * @return
     */
    @Async
    Future<User> findFirstByName(String name);

    @Async
    CompletableFuture<User> findOneByName(String name);

    @Async
    ListenableFuture<User> findOneByAge(Integer age);

    /**
     * 排序示例
     *
     * @param name
     * @param sort
     * @return
     */
    @Query("select u from User u where u.name like ?1%")
    List<User> findByAndSort(String name, Sort sort);

    /**
     * 命名查询，此方法名是无法被正确解析成给一个查询语句的，但是使用了NamedQuery，可以做到
     *
     * @param emailAddress
     * @return
     */
    @Nullable
    List<User> findEmail(String emailAddress);

    /**
     * 原始SQL
     *
     * @param name
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM USER WHERE NAME = ?1",
            countQuery = "SELECT count(*) FROM USER WHERE NAME = ?1",
            nativeQuery = true)
    Page<User> findByName(String name, Pageable pageable);


    @Modifying(clearAutomatically = true)
    @Query("update User u set u.name = ?1 where u.name = ?2")
    int setFixedNameFor(String targetName, String sourceName);

    /**
     * 按照id删除
     *
     * @param Id
     */
    void deleteById(long Id);

    /**
     * 按照id删除
     *
     * @param id
     */
    @Modifying(clearAutomatically = true)
    @Query("delete from User u where u.id = ?1")
    void deleteInBulkById(long id);
}
