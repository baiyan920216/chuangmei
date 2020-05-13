package com.chuangmei.web.dto;

import com.chuangmei.web.entity.User;

public class UserBalanceDto extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Double money_balance;
	private Long time_balance;
	private String updated_time;
	public Double getMoney_balance() {
		return money_balance;
	}
	public void setMoney_balance(Double money_balance) {
		this.money_balance = money_balance;
	}
	public Long getTime_balance() {
		return time_balance;
	}
	public void setTime_balance(Long time_balance) {
		this.time_balance = time_balance;
	}
	public String getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(String updated_time) {
		this.updated_time = updated_time;
	}
	
	
	
}
