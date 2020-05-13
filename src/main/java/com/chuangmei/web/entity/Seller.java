package com.chuangmei.web.entity;

import java.io.Serializable;

import com.chuangmei.web.core.jdbc.annotation.Key;

public class Seller implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Key
	private Long id;
	
	/**
	 * 卖方编号
	 */
	private String sellernum;
	
	/**
	 * 卖方姓名
	 */
	private String sellername;
	
	/**
	 * 卖方电话
	 */
	private String sellertel;
	
	/**
	 * 卖方支付宝帐号
	 */
	private String selleralipay;
	
	/**
	 * 卖方推荐人编号
	 */
	private String sellersupportname;
	
	/**
	 * 卖方推荐人姓名
	 */
	private String sellersuporttel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSellernum() {
		return sellernum;
	}

	public void setSellernum(String sellernum) {
		this.sellernum = sellernum;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getSellertel() {
		return sellertel;
	}

	public void setSellertel(String sellertel) {
		this.sellertel = sellertel;
	}

	public String getSelleralipay() {
		return selleralipay;
	}

	public void setSelleralipay(String selleralipay) {
		this.selleralipay = selleralipay;
	}

	public String getSellersupportname() {
		return sellersupportname;
	}

	public void setSellersupportname(String sellersupportname) {
		this.sellersupportname = sellersupportname;
	}

	public String getSellersuporttel() {
		return sellersuporttel;
	}

	public void setSellersuporttel(String sellersuporttel) {
		this.sellersuporttel = sellersuporttel;
	}
	
	

}
