package com.longing.model;

/**
 * 被观察者接口定义
 * @author zhangzhenkun
 *
 */
public interface MyObserverable {
	
	void register(MyObserver myObserver);
	
	void remove(MyObserver myObserver);
	
	void send(NewsModel newsModel);
}
