package com.chuangmei.web.core.jdbc.common;

/**
 * <br>创建日期：2015年5月18日
 * <br><b>Copyright 2015 UTOUU All Rights Reserved</b>
 * @author jinww
 * @since 1.0
 * @version 1.0
 */
public class JdbcException extends RuntimeException{

	private static final long serialVersionUID = -1956089458134530038L;

	private String errorCode;

	/**
	 * 
	 */
	public JdbcException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param errorCode 错误代码
	 * @param message 信息
	 */
	public JdbcException(String errorCode, String message){
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * @param message 信息
	 * @param cause 异常
	 */
	public JdbcException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message 信息
	 */
	public JdbcException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause 异常
	 */
	public JdbcException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @since 1.0 
	 * @return
	 * <br><b>作者： @author jinww</b>
	 * <br>创建时间：2015年5月18日 下午4:09:43
	 */
	public String getErrorCode() {
		return errorCode;
	}
	
	
}
