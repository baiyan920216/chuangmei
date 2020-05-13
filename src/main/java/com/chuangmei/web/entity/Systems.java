package com.chuangmei.web.entity;

import com.chuangmei.web.core.jdbc.annotation.Key;

public class Systems {
	
	@Key
	private Long id;
	private Long cid;
	private String syscode;
	private String sysname;
	private String sysurl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getSyscode() {
		return syscode;
	}

	public void setSyscode(String syscode) {
		this.syscode = syscode;
	}

	public String getSysname() {
		return sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

	public String getSysurl() {
		return sysurl;
	}

	public void setSysurl(String sysurl) {
		this.sysurl = sysurl;
	}
}
