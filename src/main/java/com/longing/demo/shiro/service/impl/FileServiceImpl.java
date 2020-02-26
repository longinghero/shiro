package com.longing.demo.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longing.demo.shiro.mapper.FileMapper;
import com.longing.demo.shiro.model.Files;
import com.longing.demo.shiro.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Autowired
	private FileMapper fileMapper;
	
	@Override
	public void insert(Files file) {
		
		fileMapper.insert(file);
	}

	@Override
	public void update(Files file) {
		
		fileMapper.update(file);
	}

	@Override
	public Files findById(String id) {
		// TODO Auto-generated method stub
		return fileMapper.findById(id);
	}

}
