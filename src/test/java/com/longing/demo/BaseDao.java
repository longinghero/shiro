package com.longing.demo;

public abstract class BaseDao<T> {
		
	public abstract void insert(T t);
	
	public abstract T find(String id);
	
}
