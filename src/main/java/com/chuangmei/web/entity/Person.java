package com.chuangmei.web.entity;

import com.chuangmei.web.core.jdbc.annotation.Key;

public class Person {
	
	@Key
	private Long id;
	private String openid;
	private String tel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}
