package com.dfz.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dfz.controller.UserController;

/**
 * User API Test class
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class ApplicationTest {
	
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc=MockMvcBuilders.standaloneSetup(new UserController()).build();
	}

	
	/**
	 * Test user controller
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserController() throws Exception {
		RequestBuilder request=null;
		
		// 1、get查一下user列表，应该为空
		request=get("/api/v1/users");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")))
				.andDo(MockMvcResultHandlers.print());	//此行可以print更多的细节信息
		
		
		// 2、post提交一个user
		request=post("/api/v1/users")
				.param("id", "1")
				.param("name", "程序员")
				.param("age", "20");
		mvc.perform(request)
				.andExpect(content().string(equalTo("success")));
		
		
		// 3、get获取user列表，应该有刚才插入的数据
		request=get("/api/v1/users");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"程序员\",\"age\":20}]")))
				.andDo(MockMvcResultHandlers.print());	//此行可以print更多的细节信息
		
		
		// 4、put修改id为1的user
		request=put("/api/v1/users/1")
				.param("name", "测试程序员")
				.param("age", "30");
		mvc.perform(request)
				.andExpect(content().string(equalTo("success")));
		
		
		// 5、get一个id为1的user
		request=get("/api/v1/users/1");
		mvc.perform(request)
				.andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测试程序员\",\"age\":30}")));
		
		
		// 6、del删除id为1的user
		request=delete("/api/v1/users/1");
		mvc.perform(request)
				.andExpect(content().string(equalTo("success")));
		
		
		// 7、get查一下user列表，应该为空
		request=get("/api/v1/users");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));
		
	}

}
