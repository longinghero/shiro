package com.longing.demo.shiro.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Role implements Serializable{
	
	private static final Long serialVersionUID = 1L;
	
	private String id;
	
	private String name;
	
	public Set<Permission> permission = new HashSet<Permission>();

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

	public Set<Permission> getPermission() {
		return permission;
	}

	public void setPermission(Set<Permission> permission) {
		this.permission = permission;
	}

	public static Long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
