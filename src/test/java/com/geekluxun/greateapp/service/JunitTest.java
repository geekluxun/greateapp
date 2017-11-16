package com.geekluxun.greateapp.service;

import com.alibaba.fastjson.JSON;
import com.geekluxun.greateapp.BaseTest;
import com.geekluxun.greateapp.dto.CommonResponseDto;
import com.geekluxun.greateapp.dto.UserDto;
import com.geekluxun.greateapp.spring.SpringDemo;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by luxun on 2017/11/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(value = "classpath:applicationContext.xml")
//@ContextConfiguration(classes = SpringConfig.class)
//@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@WebAppConfiguration(value = "classpath:applicationContext.xml")
//@Transactional(transactionManager = "txMgr")
@ActiveProfiles("prod") //不起作用！！！
//@TestPropertySource(value = "classpath:application.properties")
//@ProfileValueSourceConfiguration(CustomProfileValueSource.class)
public class JunitTest extends BaseTest {

    //    @Autowired
//    @Qualifier(value = "userService33")
//    UserService userService;
    @Resource(name = "springDemo11")
    SpringDemo springDemo;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    //在测试类实例化前
    @Ignore
    @BeforeClass
    public void beforeClass() {

    }

    @After
    public void after(){

    }

    /**
     * get请求
     *
     * @throws Exception
     */
    @Test
    public void testGetRequeset() throws Exception {
        this.mockMvc.perform(get("/test"));
    }

    /**
     * post请求
     *
     * @throws Exception
     */
    @Test
    public void testPostRequest() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("lu");
        userDto.setPassword("123");
        String body = JSON.toJSONString(userDto);
        MvcResult result = this.mockMvc.perform(post("/main.json").contentType(MediaType.APPLICATION_JSON).content(body)).andReturn();
        String reponseBody = result.getResponse().getContentAsString();
        CommonResponseDto dto = JSON.parseObject(reponseBody, CommonResponseDto.class);

        Assert.assertNotNull(dto);
    }


    @Commit
    @Test
    public void testProcessWithoutRollback() {

    }

    @Rollback
    @Test
    public void testProcessWithRollback() {

    }

    @BeforeTransaction
    void beforeTransaction() {
        // logic to be executed before a transaction is started
    }

    @AfterTransaction
    void afterTransaction() {
        // logic to be executed after a transaction has ended
    }

    @Test
    @Sql({"/test-schema.sql", "/test-user-data.sql"})
    public void userTest() {
        // execute code that relies on the test schema and test data
    }

    @Test
    @Sql(scripts = "/test-user-data.sql", config = @SqlConfig(commentPrefix = "`", separator = "@@"))
    public void userTest2() {
        // execute code that relies on the test data
    }


    @IfProfileValue(name = "test-groups", values = {"unit-tests", "integration-tests"})
    @Test
    public void testProcessWhichRunsForUnitOrIntegrationTestGroups() {
        // some logic that should run only for unit and integration test groups
    }

    @Timed(millis = 1000)
    public void testProcessWithOneSecondTimeout() {
        // some logic that should not take longer than 1 second to execute
    }

    @Repeat(10)
    @Test
    public void testProcessRepeatedly() {
        // ...
    }
}
