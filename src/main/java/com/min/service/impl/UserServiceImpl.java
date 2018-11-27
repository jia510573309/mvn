package com.min.service.impl;

import com.min.dao.UserDao;
import com.min.entity.UserEntity;
import com.min.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao dao;
	
	@Override
	public List<UserEntity> selectUser() {
		return dao.selectUser();
	}
}

