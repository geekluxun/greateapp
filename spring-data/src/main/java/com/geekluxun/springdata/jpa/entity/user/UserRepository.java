package com.geekluxun.springdata.jpa.entity.user;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

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
     * 注意By后的必须是User的一个属性
     *
     * @param name
     * @return
     */
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

    @Query("select u from User u where u.name like ?1%")
    List<User> findByAndSort(String name, Sort sort);

}
