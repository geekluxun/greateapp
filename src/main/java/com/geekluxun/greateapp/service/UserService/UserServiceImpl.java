package com.geekluxun.greateapp.service.UserService;

import com.geekluxun.greateapp.annotation.ParaValidator;
import com.geekluxun.greateapp.config.FooProperties;
import com.geekluxun.greateapp.dao.TUserMapper;
import com.geekluxun.greateapp.entity.TUser;
import com.geekluxun.greateapp.execption.MyException;
import com.geekluxun.greateapp.execption.ParamValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    /**
     * @Transactional默认只回滚RuntimeException异常，此方法跑出的MyException不是RuntimeException，
     * 所以，即使发生异常，也不会回滚，改成rollbackFor = Exception.class才能回滚
     * @param user
     * @throws MyException
     */
    @Override
    //@MyAnnotation
    @Transactional(rollbackFor = Exception.class, noRollbackFor = ParamValidException.class)
    public void addUser(TUser user) throws MyException {
        userMapper.insertSelective(user);

        logger.info("==========属性值==========={}" ,fooProperties);
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
    public void addUser2(TUser user){
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
     private void testaddUser(TUser user){
        userMapper.insertSelective(user);
    }

}