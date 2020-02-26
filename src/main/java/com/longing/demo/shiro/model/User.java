package com.longing.demo.shiro.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable{
	
	private static final Long serialVersionUID = 1L;
	
	private String id;
	
	private String name;
	
	private String password;
	
	public Set<Role> roles = new HashSet<Role>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public static Long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
