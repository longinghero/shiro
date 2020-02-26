package com.longing.demo.shiro.service;

import com.longing.demo.shiro.model.Files;

public interface FileService {
	

	public void insert(Files file);
	
	public void update(Files file);
	
	public Files findById(String id);
}
