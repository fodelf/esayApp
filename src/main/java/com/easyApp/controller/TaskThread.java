package com.easyApp.controller;

import com.easyApp.dao.CreateAppDao;

public class TaskThread implements Runnable{
	private Integer userId;
	
	CreateAppDao createAppDao;

	public TaskThread (Integer userId,CreateAppDao createAppDao) {
		this.userId =userId;
		this.createAppDao = createAppDao;
				
//		System.out.println("开始调用");
//		CreatApp.ok(userId);
	}
	@Override
	public void run() {
		new CreatApp(createAppDao).ok(this.userId);
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void run(String userId) {
//		
//		
//		CreatApp.ok(userId);
//		
//	}

}
