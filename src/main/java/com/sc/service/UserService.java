package com.sc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.repository.UserInfo;
import com.sc.repository.UserInfoRepository;

@Service
public class UserService {

	@Autowired
	private UserInfoRepository repository;

	public List<UserInfo> getAllUsers() {
		return repository.findAll();
	}

	public String addUser(UserInfo userInfo) {
		repository.save(userInfo);
		return "user added successfully";
	}
}
