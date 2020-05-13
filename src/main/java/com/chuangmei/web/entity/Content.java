package com.chuangmei.web.entity;

import java.io.Serializable;

import com.chuangmei.web.core.jdbc.annotation.Key;

public class Content implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Key
	private Long id;
	private String title;
	private String content;
	private String des;
	private String writer;
	private String type;
	private String createtime;
	private String showpic;
	
	private String starttime;
	private String endtime;
	
	private String flag;
	
	private Long rownum;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getShowpic() {
		return showpic;
	}
	public void setShowpic(String showpic) {
		this.showpic = showpic;
	}
	public Long getRownum() {
		return rownum;
	}
	public void setRownum(Long rownum) {
		this.rownum = rownum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
