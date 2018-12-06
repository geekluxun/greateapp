package com.geekluxun.springdata.jpa.entity.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-05 14:41
 * @Description: 注意这个类的名字默认必须是以"Impl"结尾，这样，JPA会扫描到它，把它作为自定义资源库的一部分
 * @Other:
 */
@Slf4j
@Component
public class CustomizedUserRepositoryImpl implements CustomizedUserRepository {

    @Override
    public void mySave(User user) {
        log.info("======执行了自定义的保存User方法======");
    }
}
