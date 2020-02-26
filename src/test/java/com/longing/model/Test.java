package com.longing.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Test {
	
	public static void main(String[] args){
		
		NewsProvider provider = new NewsProvider();
		User user;
		for(int i =0 ;i<10;i++){
			user = new User(""+i);
			provider.register(user);
		}
	}
}
