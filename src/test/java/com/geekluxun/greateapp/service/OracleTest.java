package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.BaseTest;
import com.geekluxun.greateapp.dao.TUserAccountMapper;
import com.geekluxun.greateapp.entity.TUserAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by luxun on 2018/5/14.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OracleTest extends BaseTest {

    @Autowired
    TUserAccountMapper tUserAccountMapper;


    @Test
    public void insertTest() {
        TUserAccount accout = new TUserAccount();
        accout.setUserId(123L);
        accout.setCreateTime(new Date());
        accout.setModifyTime(new Date());
        tUserAccountMapper.insertSelective(accout);

    }
}
