package com.longing.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NewsProvider implements MyObserverable{

	private static final long DEDAY = 2 * 1000;
	
	private List<MyObserver> list;

	public NewsProvider() {
		
		list = new ArrayList<MyObserver>();
		generateNews();
	}
	
	private void generateNews(){
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			int titleCont = 1;
			int contentCount =1;
			@Override
			public void run() {
				send(new NewsModel(titleCont++,contentCount++));
				
			}
		}, 1000, DEDAY);
	}

	@Override
	public void register(MyObserver myObserver) {
		
		if(myObserver == null){
			return;
		}
		synchronized (this) {
			if(!list.contains(myObserver)){
				list.add(myObserver);
			}
		}
		
	}

	@Override
	public void remove(MyObserver myObserver) {
		
		if(myObserver == null){
			return;
		}
		if(list.contains(myObserver)){
			list.remove(myObserver);
		}
	}

	@Override
	public void send(NewsModel newsModel) {
		for(MyObserver server:list){
			server.receive(newsModel);
		}

	}

}
