package com.geekluxun.greateapp.example.jdbc;

import com.geekluxun.greateapp.mq.activemq.producer.TestQueueMessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/31 11:06
 * Description:
 */
@Service
public class JdbcExample {

    public final static Logger logger = LoggerFactory.getLogger(JdbcExample.class);


    @Value("${db.jdbcUrl}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;


    public void testTransaction() {

        Statement stmt = null;

        java.sql.Connection conn = null;
        try {
            conn = conn = DriverManager.getConnection(url, username, password);

            // 将自动提交设置为 false，
            // 若设置为 true 则数据库将会把每一次数据更新认定为一个事务并自动提交
            conn.setAutoCommit(false);

            stmt = conn.createStatement();

            stmt.execute("UPDATE test.t_user SET  remained = 100 WHERE id = 89");


            stmt.execute("INSERT INTO test.t_user (name, password, create_time, modify_time) VALUES ('luxun', '1122', now(), now());\n");

            // 提交事务
            conn.commit();
            // 事务提交：转账的两步操作同时成功
        } catch (SQLException sqle) {
            try {
                // 发生异常，回滚在本事务中的操做
                conn.rollback();
                // 事务回滚：转账的两步操作完全撤销
                stmt.close();
                conn.close();
            } catch (Exception ignore) {

            }
            sqle.printStackTrace();
        }

    }
}
