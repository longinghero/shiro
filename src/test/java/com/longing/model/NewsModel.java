package com.longing.model;

public class NewsModel {
	
	private int titleCount;
	
	private int contentCount;
	
	public NewsModel(int titleCount,int contentCount){
		this.titleCount = titleCount;
		this.contentCount = contentCount;
	}

	public int getTitleCount() {
		return titleCount;
	}

	public void setTitleCount(int titleCount) {
		this.titleCount = titleCount;
	}

	public int getContentCount() {
		return contentCount;
	}

	public void setContentCount(int contentCount) {
		this.contentCount = contentCount;
	}
	

}
