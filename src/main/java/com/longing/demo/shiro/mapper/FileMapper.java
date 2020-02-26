package com.longing.demo.shiro.mapper;

import java.util.Map;

import com.longing.demo.shiro.model.Files;

public interface FileMapper {

	public void insert(Files file);
	
	public void update(Files file);
	
	public Files findById(String id);
}
