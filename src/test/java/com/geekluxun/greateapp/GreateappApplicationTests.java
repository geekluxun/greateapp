package com.geekluxun.greateapp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.geekluxun.greateapp.entity.TUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreateappApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testJson(){
		List list = new ArrayList();
		TUser user = new TUser();
		user.setId(11L);
		user.setName("luxun");
		user.setPassword("123");
		list.add(user);

		user = new TUser();
		user.setId(22L);
		user.setName("luxun");
		user.setPassword("456");
		list.add(user);

		String s = JSON.toJSONString(list);

		list = JSON.parseObject(s, new TypeReference<List<TUser>>() {});

		System.out.println(list.toString());
	}

}
