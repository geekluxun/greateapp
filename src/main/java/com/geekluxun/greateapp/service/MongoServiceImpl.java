package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by luxun on 2017/10/17.
 */
@Service
public class MongoServiceImpl implements MongoService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void saveUser(TUser user) {
        mongoTemplate.save(user);
    }


}
