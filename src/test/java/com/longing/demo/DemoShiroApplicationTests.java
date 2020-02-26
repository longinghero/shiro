package com.longing.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.longing.demo.shiro.DemoShiroApplication;
import com.longing.demo.shiro.model.Files;
import com.longing.demo.shiro.service.FileService;

@RunWith(SpringJUnit4ClassRunner.class)

@SpringBootTest(classes=DemoShiroApplication.class)
public class DemoShiroApplicationTests {

	@Autowired
	private FileService fileService;

	@Test
	public void contextLoads() throws Exception {

		Files file = in();
		out(file);

	}
	public void out(Files files) throws Exception{
	
		byte[] bytes = (byte[]) files.getFileContent();
		
		String fileName = "C:/Users/zhangzhenkun/Desktop/test" + files.getSuffix();
		File file = new File(fileName);
		OutputStream outputStream = new FileOutputStream(file);
		outputStream.write(bytes);
		outputStream.close();
	}
	public Files in() throws IOException{
		File file = new File("C:/Users/zhangzhenkun/Desktop/RabbitMQ实战教程.docx");
		Files files= new Files();
		files.setName(file.getName());
		String suffix = file.getName().substring(file.getName().lastIndexOf("."),file.getName().length()); 
		files.setSuffix(suffix);
		byte[] fileContent = null;
		FileInputStream is = new FileInputStream(file);
		fileContent = new byte[is.available()];
		is.read(fileContent);

		files.setFileContent(fileContent);
		fileService.insert(files);


		is.close();
		return files;
	}

}
