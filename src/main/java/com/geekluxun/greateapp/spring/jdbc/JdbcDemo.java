package com.geekluxun.greateapp.spring.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by luxun on 2018/1/15.
 */
public class JdbcDemo {
    ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>() {
        @Override
        protected Connection initialValue() {
            Connection connection = null;

            try {
                connection = DriverManager.getConnection("www.geekluxun.com");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return connection;
        }
    };
    @Autowired
    JdbcTemplate jdbcTemplate;

    private void f1() {}

    public static void main(String[] argc) {
        JdbcDemo jdbcDemo = new JdbcDemo();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
