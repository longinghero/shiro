package com.longing.demo.shiro.service;

import com.longing.demo.shiro.model.User;

public interface UserService {
	
	User findByName(String name);
}
