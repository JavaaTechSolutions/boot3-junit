package com.sc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.sc.repository.UserInfo;
import com.sc.repository.UserInfoRepository;
import com.sc.service.UserService;

@SpringBootTest
public class TestUserService {

	@Mock
	private UserInfoRepository repo;

	@InjectMocks
	private UserService userService;

	@Test
	public void testGetAllUsers() {
		UserInfo user = new UserInfo();
		List<UserInfo> users = new ArrayList<>();
		users.add(user);

		when(repo.findAll()).thenReturn(users);

		List<UserInfo> response = userService.getAllUsers();
		assertNotNull(response);
	}
	
	@Test
	public void testAddUsers() {
		UserInfo user = new UserInfo();
		List<UserInfo> users = new ArrayList<>();
		users.add(user);

		when(repo.save(any(UserInfo.class))).thenReturn(user);

		String response = userService.addUser(user);
		assertNotNull(response);
		assertEquals("user added successfully", response);
	}

}
