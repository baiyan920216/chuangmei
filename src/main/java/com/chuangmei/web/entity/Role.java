package com.chuangmei.web.entity;

import com.chuangmei.web.core.jdbc.annotation.Key;

public class Role {
	
	@Key
	private Long id;
	private String name;
	private String remark;
	private String auths;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAuths() {
		return auths;
	}
	public void setAuths(String auths) {
		this.auths = auths;
	}
}
