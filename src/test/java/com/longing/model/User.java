package com.longing.model;

public class User implements MyObserver{
	
	private String name;
	
	public User(String name){
		this.name = name;
	}
	@Override
	public void receive(NewsModel newsModel) {
		
		System.out.println("user:" +name+ "receive:title:"+newsModel.getTitleCount()+"content:"+newsModel.getContentCount());
		
	}

}
