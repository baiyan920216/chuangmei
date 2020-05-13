package com.chuangmei.web.entity;

import com.chuangmei.web.core.jdbc.annotation.Key;
import com.chuangmei.web.core.jdbc.annotation.NotRecord;
import com.chuangmei.web.utils.CommonUtils;

import java.io.Serializable;


public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Key
	//会员帐号
	private Long id;
	
	private String uname;
	
	private String upass;
	
	//会员姓名
	private String name;
	
	private String tel;
	
	private String mail;
	
	//支付宝帐号
	private String alipay;
	//二级密码
	private String spass;
	
	/**
	 * 角色ID
	 */
	private Integer roletype;
	
	/**
	 * 推荐编号
	 */
	private String supportid;
	
	private String address;
	
	private String activetime;
	
	
	/**
	 * 是否锁定，默认0，锁定1
	 */
	private Integer locked;

	
	@NotRecord
	private String isLock;
	
	@NotRecord
	private String treeNode;
	
	public String getTreeNode() {
		return this.uname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpass() {
		return upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}
	
	public void MD5Password(){
		this.upass = CommonUtils.md5(this.upass);
		this.spass = CommonUtils.md5(this.spass);
	}



	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getRoletype() {
		return roletype;
	}

	public void setRoletype(Integer roletype) {
		this.roletype = roletype;
	}

	public String getSupportid() {
		return supportid;
	}

	public void setSupportid(String supportid) {
		this.supportid = supportid;
	}


	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public String getIsLock() {
		Integer tmp_lock = this.locked;
		if(tmp_lock!=null&&tmp_lock==1){
			this.isLock = "已锁定";
		}else if(tmp_lock!=null&&tmp_lock==2){
			this.isLock = "未激活";
		}else if(tmp_lock!=null&&tmp_lock==0){
			this.isLock = "未锁定";
		}
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlipay() {
		return alipay;
	}

	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}

	public String getSpass() {
		return spass;
	}

	public void setSpass(String spass) {
		this.spass = spass;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getActivetime() {
		return activetime;
	}

	public void setActivetime(String activetime) {
		this.activetime = activetime;
	}
	
	
	
//public static void main(String[] args) {
//	System.out.println(CommonUtils.md5("xiaolimao"));
//}

}
