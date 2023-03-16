package com.sc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import com.sc.controller.UserController;
import com.sc.repository.UserInfo;
import com.sc.repository.UserInfoRepository;
import com.sc.service.UserService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = {"com.sc.controller", "com.sc.service"})
@Import(UserController.class)
public class TestUserController {
	
	@LocalServerPort
	private int port;
	
	@MockBean
	private UserInfoRepository repo;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Mock
	private UserService userService;
	
	@Test
	@DisplayName("Testing is running API")
	public void testIsRunning() throws MalformedURLException {
		URL url = new URL("http://localhost:" + port + "/api/isRunning");
		ResponseEntity<String> response = restTemplate.getForEntity(url.toString(), String.class);
		assertEquals("Service is running", response.getBody());
	}
	
	@Test
	@DisplayName("Testing getAllUser API")
	public void testGetAllUser() throws MalformedURLException {
		UserInfo user = new UserInfo();
		List<UserInfo> users = new ArrayList<>();
		users.add(user);
		
		when(userService.getAllUsers()).thenReturn(users);
		
		URL url = new URL("http://localhost:" + port + "/api/getAllUser");
		List<UserInfo> response = restTemplate.getForObject(url.toString(), List.class);
		
		assertNotNull(response);
	}
	
	@Test
	@DisplayName("Testing add user API")
	public void testAddUser() throws MalformedURLException {
		UserInfo user = new UserInfo();
		
		HttpEntity<UserInfo> request = new HttpEntity<>(user);
		
		when(userService.addUser(any())).thenReturn("user added successfully");
		
		URL url = new URL("http://localhost:" + port + "/api/addUser");
		String response = restTemplate.postForObject(url.toString(), request, String.class);
		
		assertNotNull(response);
		assertEquals("user added successfully", response);
	}
	
	
	
	

}
