package com.longing.demo.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longing.demo.shiro.mapper.UserMapper;
import com.longing.demo.shiro.model.User;
import com.longing.demo.shiro.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		User user = userMapper.findByName(name);
		/*if(user != null){
			user = userMapper.findById(user.getId());
		}*/
		return user;
	}

}
