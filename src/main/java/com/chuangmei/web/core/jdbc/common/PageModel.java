package com.chuangmei.web.core.jdbc.common;

import java.util.List;


/** 
 * @author 陆卫东 E-mail: 770141401@qq.com
 * @version 创建时间：2013-8-30 
 *  类说明 
 */
public class PageModel<T> extends BaseMessageModel{
	
	private int pageNumber = 1; // 当前页
	private int pageSize = 10; // 页大小	
	private long pageCount = 0; // 总页数
	private long total = 0;
	private String sort; // 排序字段
	private String order; // 排序规则
	private List<T> rows;// 
	
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
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
}
