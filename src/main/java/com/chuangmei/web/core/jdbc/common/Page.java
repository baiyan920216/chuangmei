package com.chuangmei.web.core.jdbc.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * <br>
 * 创建日期：2015年10月19日 <br>
 * <b>Copyright 2015 UTOUU All Rights Reserved</b>
 * 
 * @author heyman
 * @since 1.0
 * @version 1.0
 * @param <T>
 *            return
 */
@JsonIgnoreProperties(value={"enErrorMsg"})
public class Page<T> {

	private long total = 0;
	private List<T> data;
	private Boolean success; // 消息状态
	private String errorMsg; // 消息提示
	private String enErrorMsg; // 消息提示
	private Long iTotalRecords;
	private Long iTotalDisplayRecords;
	private long pageCount = 0; // 总页数
	private int pageNumber = 1; // 当前页
	private int pageSize = 10; // 页大小

	/**
	 * 
	 */
	public Page() {

	}

	/**
	 * @param total
	 *            总数
	 * @param rows
	 *            数据
	 */
	public Page(long total, List<T> rows) {
		this.total = total;
		this.data = rows;
	}

	/**
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(Long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public Long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(Long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	/**
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getEnErrorMsg() {
		return enErrorMsg;
	}

	public void setEnErrorMsg(String enErrorMsg) {
		this.enErrorMsg = enErrorMsg;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg
	 *            the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @since 1.0
	 * @param success
	 *            success
	 * @param errorMsg
	 *            errorMsg <br>
	 *            <b>作者： @author FG</b> <br>
	 *            创建时间：2015年10月15日 下午5:23:40
	 */
	public void initSuccess(Boolean success, String errorMsg) {
		this.success = success;
		this.errorMsg = errorMsg;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
