package com.longing.demo.shiro.model;

import java.io.Serializable;

public class Files implements Serializable{
	
	private static final Long serialVersionUID = 1L;
	
	private int id;
	
	private String name;
	
	private String suffix;
	
	private Object fileContent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Object getFileContent() {
		return fileContent;
	}

	public void setFileContent(Object fileContent) {
		this.fileContent = fileContent;
	}


	
}
