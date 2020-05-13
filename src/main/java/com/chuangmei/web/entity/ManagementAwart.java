package com.chuangmei.web.entity;

import java.io.Serializable;

import com.chuangmei.web.core.jdbc.annotation.Key;

public class ManagementAwart implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Key
	private Long id;
	private Long uid;
	private String uname;
	private String useruname;
	private String finishtime;
	private Long suid;
	private String subuname;
	private String subuseruname;
	private String type;
	private String flag;
	private Long orderid;
	private Long money;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUseruname() {
		return useruname;
	}
	public void setUseruname(String useruname) {
		this.useruname = useruname;
	}
	public String getFinishtime() {
		
		return finishtime.substring(0, 10);
	}
	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}
	public Long getSuid() {
		return suid;
	}
	public void setSuid(Long suid) {
		this.suid = suid;
	}
	public String getSubuname() {
		return subuname;
	}
	public void setSubuname(String subuname) {
		this.subuname = subuname;
	}
	public String getSubuseruname() {
		return subuseruname;
	}
	public void setSubuseruname(String subuseruname) {
		this.subuseruname = subuseruname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Long getOrderid() {
		return orderid;
	}
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	
}
