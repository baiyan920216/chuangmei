package com.chuangmei.web.core.jdbc.common;

public class BaseMessageModel {
	
	private Boolean success;  // 消息状态
	private String msg;  // 消息提示
	private String code;
	
	public void initSuccess(Boolean success,String msg){
		this.success = success;
		this.msg = msg;
	}
	
	public void initCode(String code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public Boolean isSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
