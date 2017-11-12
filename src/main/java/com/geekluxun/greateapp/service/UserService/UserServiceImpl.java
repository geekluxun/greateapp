package com.geekluxun.greateapp.service.UserService;

import com.geekluxun.greateapp.annotation.ParaValidator;
import com.geekluxun.greateapp.dao.TUserMapper;
import com.geekluxun.greateapp.dto.UserDto;
import com.geekluxun.greateapp.entity.TUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by luxun on 2017/9/3.
 */
//@Service  如果不指明bean的名字，默认的名字时uerServiceImpl,通过这个名字注入此bean到其他bean中
//@Service("userService33")指明名字为userService33
@Service("userService33")
@ParaValidator
public class UserServiceImpl implements UserService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    TUserMapper userMapper;

    @Resource(name = "userService33")
    UserService userService;

    @Override
    //@MyAnnotation
    public void addUser(TUser user) {
        userMapper.insert(user);

        //以下两种方式调用不一样，前一种不会被动态代理，后一种会被动态代理
        //testInnerMethodCall();
        //userService.testInnerMethodCall();
    }

    @Override
    public Boolean isSucceed() {
        return false;
    }

    public void exceptionTest() throws Exception{
        throw new RuntimeException("在UserService.exceptionTest中抛出异常示例!!");
    }

    public void testInnerMethodCall(){
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
    public void testValidate(@NotNull @Valid UserDto dto) {
        logger.info("================ testValidate ================:");
    }
}
