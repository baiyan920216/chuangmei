package com.chuangmei.web.entity;

import java.io.Serializable;

import com.chuangmei.web.core.jdbc.annotation.Key;
import com.chuangmei.web.core.jdbc.annotation.NotRecord;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Key
	private Long id;
	
	private Long cid;
	
	
	/**
	 * 买方编号
	 */
	private Long buyernum;
	
	/**
	 * 买方姓名
	 */
	private String buyername;
	
	/**
	 * 买方用户名
	 */
	private String buyeruname;
	
	/**
	 * 买方电话
	 */
	private String buyertel;
	
	/**
	 * 买方支付宝帐号
	 */
	private String buyeralipay;
	
	/**
	 * 买房推荐人姓名
	 */
	private String buyersuportname;
	
	/**
	 * 买方推荐人电话
	 */
	private String buyersuporttel;
	
	/**
	 * 地址
	 */
	private String buyeraddress;
	
	/**
	 * 订单创建时间
	 */
	private String orderdate;
	
	/**
	 * 订单打款结束时间
	 */
	private String orderenddate;
	
	/**
	 * 订单打款开始时间
	 */
	private String orderstartdate;
	
	/**
	 * 打款凭证
	 */
	private String proof;
	
	/**
	 * 上传凭证截止时间
	 */
	private String proofendtime;
	
	/**
	 * 订单状态。0：预约；1：预约成功；2：上传凭证；3：打款完成
	 */
	private String status;
	
	
	/**
	 * 订单状态描述
	 */
	@NotRecord
	private String statusDes;
	
	
	public String getStatusDes() {
		
		if(this.status.equals("0")){
			this.statusDes = "预约中";
		}
		else if(this.status.equals("1")){
			this.statusDes = "预约成功";
		}
		else if(this.status.equals("2")){
			//暂时废弃
			this.statusDes = "匹配完成";
		}else if(this.status.equals("3")){
			this.statusDes = "已上传打款凭证";
		}else if(this.status.equals("4")){
			this.statusDes = "已完成收款";
		}
		
		return statusDes;
	}


	/**
	 * 打款金额
	 */
	private String money;
	
	/**
	 * 收款金额
	 */
	private String receivemoney;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getBuyernum() {
		return buyernum;
	}

	public void setBuyernum(Long buyernum) {
		this.buyernum = buyernum;
	}

	public String getBuyername() {
		return buyername;
	}

	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}

	public String getBuyertel() {
		return buyertel;
	}

	public void setBuyertel(String buyertel) {
		this.buyertel = buyertel;
	}

	public String getBuyersuportname() {
		return buyersuportname;
	}

	public void setBuyersuportname(String buyersuportname) {
		this.buyersuportname = buyersuportname;
	}

	public String getBuyersuporttel() {
		return buyersuporttel;
	}

	public void setBuyersuporttel(String buyersuporttel) {
		this.buyersuporttel = buyersuporttel;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getOrderenddate() {
		return orderenddate;
	}

	public void setOrderenddate(String orderenddate) {
		this.orderenddate = orderenddate;
	}

	public String getOrderstartdate() {
		return orderstartdate;
	}

	public void setOrderstartdate(String orderstartdate) {
		this.orderstartdate = orderstartdate;
	}

	public String getProof() {
		return proof;
	}

	public void setProof(String proof) {
		this.proof = proof;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBuyeralipay() {
		return buyeralipay;
	}

	public void setBuyeralipay(String buyeralipay) {
		this.buyeralipay = buyeralipay;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getProofendtime() {
		return proofendtime;
	}

	public void setProofendtime(String proofendtime) {
		this.proofendtime = proofendtime;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getReceivemoney() {
		return receivemoney;
	}

	public void setReceivemoney(String receivemoney) {
		this.receivemoney = receivemoney;
	}

	public String getBuyeruname() {
		return buyeruname;
	}

	public void setBuyeruname(String buyeruname) {
		this.buyeruname = buyeruname;
	}

	public String getBuyeraddress() {
		return buyeraddress;
	}

	public void setBuyeraddress(String buyeraddress) {
		this.buyeraddress = buyeraddress;
	}


}
