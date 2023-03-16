package com.sc.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sc.controller.UserController;
import com.sc.repository.UserInfoRepository;

@WebMvcTest(UserController.class)
@ComponentScan(basePackages = {"com.sc.controller", "com.sc.service"})
public class TestUserControllerWithMockMVC {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserInfoRepository repo;
	
	@Test
	public void isRunning() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/isRunning")
				.accept(MediaType.ALL))
				.andDo(print())
				.andExpect(status().isOk());
	}
 
}