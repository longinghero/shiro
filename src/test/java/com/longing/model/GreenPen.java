package com.longing.model;

public class GreenPen implements DrawApi{

	@Override
	public void draw(int radius, int x, int y) {
		
		System.out.println("绿色画笔:"+radius+"x="+x+"y="+y);
		
	}

}
