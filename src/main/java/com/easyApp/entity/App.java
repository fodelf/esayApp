package com.easyApp.entity;

import java.util.Date;

public class App {
	private int appId;
	private int userId;
	private String appDec;
	private String appUrl;
	private Date appCreateTime;
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAppDec() {
		return appDec;
	}
	public void setAppDec(String appDec) {
		this.appDec = appDec;
	}
	public String getAppUrl() {
		return appUrl;
	}
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	public Date getAppCreateTime() {
		return appCreateTime;
	}
	public void setAppCreateTime(Date appCreateTime) {
		this.appCreateTime = appCreateTime;
	}
	
}
