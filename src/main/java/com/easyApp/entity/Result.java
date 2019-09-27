package com.easyApp.entity;

public class Result {
	private String msg;
	private String code;
	private Object data;
	public String getmsg() {
		return msg;
	}
	public void setmsg(String msg) {
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public static Result ok(Object data) {
		return new Result(data);
	}
	
	public static Result err(Object data) {
		return new Result("000001","错误",data);
	}
	private Result(Object data) {
		this.code = "000000";
    	this.msg ="成功";
    	this.data = data;
	}
	private Result(String code ,String msg ,Object data) {
		this.code = code;
    	this.msg = msg;
    	this.data = data;
	}
}
