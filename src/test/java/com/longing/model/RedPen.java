package com.longing.model;

public class RedPen implements DrawApi{

	@Override
	public void draw(int radius, int x, int y) {
		
		System.out.println("红色画笔:"+radius+"x="+x+"y="+y);
		
	}

}
