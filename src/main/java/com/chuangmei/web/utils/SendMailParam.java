package com.chuangmei.web.utils;

public class SendMailParam {
	
	private String to;
	private String from;
	private String subject;
	private String message;
	private boolean auth;
	
	public SendMailParam(String to,String from,String subject,String message,boolean auth){
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.message = message;
		this.auth = auth;
	}

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean getAuth() {
		return auth;
	}
	public void setAuth(boolean auth) {
		this.auth = auth;
	}

}
