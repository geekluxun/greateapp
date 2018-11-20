package com.geekluxun.greateapp.service.UserService;

import com.alibaba.fastjson.JSON;
import com.geekluxun.greateapp.annotation.ParaValidator;
import com.geekluxun.greateapp.config.FooProperties;
import com.geekluxun.greateapp.dao.TUserMapper;
import com.geekluxun.greateapp.entity.TUser;
import com.geekluxun.greateapp.execption.MyException;
import com.geekluxun.greateapp.execption.ParamValidException;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by luxun on 2017/9/3.
 */
//@Service  如果不指明bean的名字，默认的名字时uerServiceImpl,通过这个名字注入此bean到其他bean中
//@Service("userService33")指明名字为userService33
@Service("userService33")
@ParaValidator
//@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    FooProperties fooProperties;

    @Autowired
    TUserMapper userMapper;

    @Resource(name = "userService33")
    UserService userService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * @param user
     * @throws MyException
     * @Transactional默认只回滚RuntimeException异常，此方法跑出的MyException不是RuntimeException， 所以，即使发生异常，也不会回滚，改成rollbackFor = Exception.class才能回滚
     * <p>
     * 此处之所以加value = "transactionManager" 是因为spring管理的事务管理器有多个情况下，指明其具体是哪个
     */
    @Override
    //@MyAnnotation
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, noRollbackFor = ParamValidException.class)
    public void addUser(TUser user) throws MyException {
        userMapper.insertSelective(user);

        logger.info("==========属性值==========={}", fooProperties);
        user.setName("luxun555");
        //addUser2(user);
        testaddUser(user);
        throw new MyException();
        //int i = 0/0;

        //testaddUser(user);

        //以下两种方式调用不一样，前一种不会被动态代理，后一种会被动态代理
        //testInnerMethodCall();
        //userService.testInnerMethodCall();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addUser2(TUser user) {
        userMapper.insertSelective(user);
        //int i = 0/0;
    }

    @Override
    public Boolean isSucceed() {
        return false;
    }

    public void exceptionTest() throws Exception {
        throw new RuntimeException("在UserService.exceptionTest中抛出异常示例!!");
    }

    public void testInnerMethodCall() {
        logger.info("================ 在内部调用方法示例 ================");
    }

    @Override
    public void testString(String argc, Map argc2) {
        logger.info("================ testString ================:" + argc);
    }

    @Override
    public void testAopArgsAnnotation(TUser user, TUser user2) {
        logger.info("================ testAopArgsAnnotation ================:");
    }

    @Override
    public List<TUser> queryByTime(Date beforeTime) {
        List<TUser> users = userMapper.selectByTime(beforeTime);
        return users;
    }

    @Override
    public TUser queryById(long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TUser> queryAll() {
        return userMapper.selectAll();
    }

    @Override
    @Transactional
    public int updateUser(TUser user) {
        return userMapper.updateRemind(user);
    }

    /**
     * JDBC 各种测试
     */
    @Override
    public void testJdbc() {
        //jdbcDemo1();
        //jdbcDemo2();
        //jdbcDemo3();
        //jdbcDemo4();
        //jdbcDemo5();
        jdbcDemo6();
    }

    @Override
    public void testMybatisCache() {
        TUser user = userMapper.selectByPrimaryKey(89L);
        user = userMapper.selectByPrimaryKey(89L);
        user = userMapper.selectByPrimaryKey(89L);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void testaddUser(TUser user) {
        userMapper.insertSelective(user);
    }


    private void jdbcDemo1() {
        String sql = "SELECT count(*) FROM test.t_user";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        logger.info("result count:" + count);
    }


    /**
     * JDBC 返回一行结果集 和JavaBean 映射
     */
    private void jdbcDemo2() {
        String sql = "SELECT * FROM test.t_user WHERE id = 89";
        TUser user = jdbcTemplate.queryForObject(sql, new UserRowMapper());
        logger.info("result:" + JSON.toJSONString(user));
    }

    /**
     * JDBC 返回多行结果集
     */
    private void jdbcDemo3() {
        String sql = "SELECT * FROM test.t_user";
        List user = jdbcTemplate.queryForList(sql);
        logger.info("result:" + JSON.toJSONString(user));
    }


    /**
     * JDBC 多表联合查询（其中同名字段需要起个别名，否则会存在后面覆盖前面同名字段问题）
     */
    private void jdbcDemo4() {
        String sql = "SELECT a.create_time ac, b.create_time bc FROM test.t_user a INNER JOIN test.t_test_slow_query  b on a.id = b.id WHERE a.name = 'luxun'";
        List user = jdbcTemplate.queryForList(sql);
        logger.info("result:" + JSON.toJSONString(user));
    }


    /**
     * JDBC 函数计算
     */
    private void jdbcDemo5() {
        String sql = "SELECT count(*) as count, sum(a.id) as ids FROM test.t_user a INNER JOIN test.t_test_slow_query  b on a.id = b.id WHERE a.name = 'luxun'";
        List user = jdbcTemplate.queryForList(sql);
        logger.info("result:" + JSON.toJSONString(user));
    }


    /**
     * JDBC 返回多行结果集 和JavaBean 映射
     */
    private void jdbcDemo6() {
        List<TUser> userList = new ArrayList<>();
        String sql = "SELECT create_time createTime ,modify_time modifyTime FROM test.t_user";

        /**
         * 这是一个很好的抽象，我们可以遍历这个list，已json形式输出给客户端
         */
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        try {
            for (Map<String, Object> map : list) {
                TUser user = new TUser();
                BeanUtils.populate(user, map); //map 转 bean!!!
                userList.add(user);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        logger.info("result:" + JSON.toJSONString(userList));
    }


    public class UserRowMapper implements RowMapper<TUser> {
        @Override
        public TUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            TUser user = new TUser();

            user.setPassword(rs.getString("password"));
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setCreateTime(rs.getTimestamp("create_time"));
            user.setModifyTime(rs.getTimestamp("modify_time"));
            user.setRemained(rs.getInt("remained"));
            user.setVersion(rs.getInt("version"));

            return user;
        }
    }


}