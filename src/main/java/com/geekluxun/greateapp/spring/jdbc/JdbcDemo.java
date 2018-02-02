package com.geekluxun.greateapp.spring.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import com.geekluxun.greateapp.entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

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


    /**
     * NamedParameterJdbcTemplate wraps a JdbcTemplate to provide named parameters instead of the traditional JDBC "?" placeholders. This approach provides better documentation and ease of use when you have multiple parameters for an SQL statement.
     */
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private void f1() {}

    public static void main(String[] argc) {
        JdbcDemo jdbcDemo = new JdbcDemo();


    }


    public void test1() {
        this.jdbcTemplate.update(
                "INSERT INTO t_actor (first_name, last_name) VALUES (?, ?)",
                "Leonor", "Watling");


        this.jdbcTemplate.update(
                "delete from actor where id = ?",
                Long.valueOf("1122"));


        this.jdbcTemplate.execute("create table mytable (id integer, name varchar(100))");

    }


    public int countOfActorsByFirstName(String firstName) {

        String sql = "select count(*) from T_ACTOR where first_name = :first_name";

        Map<String, String> namedParameters = Collections.singletonMap("first_name", firstName);

        return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }


    public int countOfActors(TUser exampleActor) {

        // notice how the named parameters match the properties of the above 'Actor' class
        String sql = "select count(*) from T_ACTOR where first_name = :firstName and last_name = :lastName";

        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(exampleActor);

        return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }


    /**
     * 批量更新 每批100个
     *
     * @param users
     * @return
     */
    public int[][] batchUpdate(final Collection<TUser> users) {
        int[][] updateCounts = jdbcTemplate.batchUpdate(
                "update t_actor set first_name = ?, last_name = ? where id = ?",
                users,
                100,
                new ParameterizedPreparedStatementSetter<TUser>() {
                    public void setValues(PreparedStatement ps, TUser argument) throws SQLException {
                        ps.setString(1, argument.getName());
                        ps.setString(2, argument.getPassword());
                        ps.setLong(3, argument.getId().longValue());
                    }
                });
        return updateCounts;
    }
}

