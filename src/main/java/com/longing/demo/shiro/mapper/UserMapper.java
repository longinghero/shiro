package com.longing.demo.shiro.mapper;

import com.longing.demo.shiro.model.User;

public interface UserMapper {
	
	User findByName(String name);
	
	User findById(String id);
}
