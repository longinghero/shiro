package com.longing.demo.shiro.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.longing.demo.shiro.model.Files;
import com.longing.demo.shiro.service.FileService;
import com.mysql.cj.jdbc.Blob;


@Controller
@RequestMapping("/upload")
public class FileUploadController {
	
	@Autowired
	private FileService fileService;
	
	private static Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@RequestMapping("/file")
	public String upload(MultipartFile  file) throws IOException, SQLException{
		
		String fileName = file.getOriginalFilename();
		InputStream in = file.getInputStream();
		
		Files files= new Files();
		files.setName(fileName);
		fileService.insert(files);
		Blob blob = (Blob) files.getFileContent();
		OutputStream os = blob.setBinaryStream(0);
		os.write(file.getBytes());
		return null;
	}
}
