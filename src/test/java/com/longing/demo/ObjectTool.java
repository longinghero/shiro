package com.longing.demo;

import java.util.ArrayList;
import java.util.List;

public class ObjectTool {
	
	public <T> void show(T t){
		System.out.println(t);
	}
	
	public void test(List<? extends Number> list){
		
		for(int i =0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	
	public static void main(String[] args){
		
		ObjectTool object = new ObjectTool();
		List<Integer> list = new ArrayList<Integer>();
		list.add(4);
		
	}
}
