package com.geekluxun.greateapp.service;

import com.geekluxun.greateapp.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by luxun on 2017/11/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockitoTest extends BaseTest{


    @Test
    public void test(){
        // 创建mock对象
        List mockedList = Mockito.mock(List.class);

        // 设置mock对象的行为 － 当调用其get方法获取第0个元素时，返回"one"
        Mockito.when(mockedList.get(0)).thenReturn("one");

        // 使用mock对象 － 会返回前面设置好的值"one"，即便列表实际上是空的
        String str = (String) mockedList.get(0);

        Assert.assertTrue("one".equals(str));
        Assert.assertTrue(mockedList.size() == 0);

        // 验证mock对象的get方法被调用过，而且调用时传的参数是0
        Mockito.verify(mockedList).get(0);

    }

    @Test
    public void count() throws Exception {
        Duplicator counter = mock(Counter.class);
        Answer<Integer> answer = new Answer<Integer>() {
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
                return ((String) invocationOnMock.getArguments()[0]).length();
            }
        };
        when(counter.count(anyString())).thenAnswer(answer);
    }

}
