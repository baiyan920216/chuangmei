package com.chuangmei.web.core.export.exception;

/**
 * @ClassName: ExportException
 * @Description: 导出异常
 * @author gang.fan
 * @date 2014年12月19日 上午10:27:07
 * 
 */
public class ExportException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * @Fields serialVersionUID : TODO
	 */

	public ExportException(String msg) {
		super(msg);
	}

	public ExportException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
