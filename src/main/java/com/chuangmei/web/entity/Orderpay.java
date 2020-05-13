package com.chuangmei.web.entity;

import java.io.Serializable;

import com.chuangmei.web.core.jdbc.annotation.Key;
import com.chuangmei.web.core.jdbc.annotation.NotRecord;

public class Orderpay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Key
	private Long id;
	private Long buyerorderid;
	private Long sellerorderid;
	private Long buyeruserid;
	private String buyeruname;
	private String buyername;
	private String buyersupportname;
	private String buyersupporttel;
	private String buyeraddress;
	private Float paymoney;
	private String pairtime;
	private String paydate;
	private String buyeralipay;
	private String beginpaydate;
	private String selleralipay;
	private String selleruname;
	private String sellername;
	private String sellertel;
	private Long selleruserid;
	private String sellersupportname;
	private String sellersupporttel;
	private String selleraddress;
	
	private String proof;
	private String prooftime;
	private String status;
	@NotRecord
	private String statusDes;
	
	


	public String getStatusDes() {
		if(this.status.equals("0")){
			this.statusDes = "未付款";
		}else if(this.status.equals("1")){
			this.statusDes = "已付款";
		}else if(this.status.equals("2")){
			this.statusDes = "已收款";
		}
		return statusDes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProof() {
		return proof;
	}
	public void setProof(String proof) {
		this.proof = proof;
	}
	public String getProoftime() {
		return prooftime;
	}
	public void setProoftime(String prooftime) {
		this.prooftime = prooftime;
	}
	public String getSelleruname() {
		return selleruname;
	}
	public void setSelleruname(String selleruname) {
		this.selleruname = selleruname;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBuyerorderid() {
		return buyerorderid;
	}
	public void setBuyerorderid(Long buyerorderid) {
		this.buyerorderid = buyerorderid;
	}
	public Long getSellerorderid() {
		return sellerorderid;
	}
	public void setSellerorderid(Long sellerorderid) {
		this.sellerorderid = sellerorderid;
	}
	public Long getBuyeruserid() {
		return buyeruserid;
	}
	public void setBuyeruserid(Long buyeruserid) {
		this.buyeruserid = buyeruserid;
	}
	public String getBuyeruname() {
		return buyeruname;
	}
	public void setBuyeruname(String buyeruname) {
		this.buyeruname = buyeruname;
	}
	public String getBuyername() {
		return buyername;
	}
	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}
	public Float getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(Float paymoney) {
		this.paymoney = paymoney;
	}
	public String getPairtime() {
		return pairtime;
	}
	public void setPairtime(String pairtime) {
		this.pairtime = pairtime;
	}
	public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public String getBuyeralipay() {
		return buyeralipay;
	}
	public void setBuyeralipay(String buyeralipay) {
		this.buyeralipay = buyeralipay;
	}
	public String getBeginpaydate() {
		return beginpaydate;
	}
	public void setBeginpaydate(String beginpaydate) {
		this.beginpaydate = beginpaydate;
	}
	public String getSelleralipay() {
		return selleralipay;
	}
	public void setSelleralipay(String selleralipay) {
		this.selleralipay = selleralipay;
	}
	public Long getSelleruserid() {
		return selleruserid;
	}
	public void setSelleruserid(Long selleruserid) {
		this.selleruserid = selleruserid;
	}
	public String getBuyersupportname() {
		return buyersupportname;
	}
	public void setBuyersupportname(String buyersupportname) {
		this.buyersupportname = buyersupportname;
	}
	public String getBuyersupporttel() {
		return buyersupporttel;
	}
	public void setBuyersupporttel(String buyersupporttel) {
		this.buyersupporttel = buyersupporttel;
	}
	public String getSellersupportname() {
		return sellersupportname;
	}
	public void setSellersupportname(String sellersupportname) {
		this.sellersupportname = sellersupportname;
	}
	public String getSellersupporttel() {
		return sellersupporttel;
	}
	public void setSellersupporttel(String sellersupporttel) {
		this.sellersupporttel = sellersupporttel;
	}
	public String getBuyeraddress() {
		return buyeraddress;
	}
	public void setBuyeraddress(String buyeraddress) {
		this.buyeraddress = buyeraddress;
	}
	public String getSelleraddress() {
		return selleraddress;
	}
	public void setSelleraddress(String selleraddress) {
		this.selleraddress = selleraddress;
	}

}
