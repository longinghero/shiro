package com.longing.model;

public class Circle extends Shape{
	
	private int radius;
	
	protected Circle(DrawApi drawApi,int radius) {
		super(drawApi);
		this.radius = radius;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		drawApi.draw(radius, 0,0);
	}

}
