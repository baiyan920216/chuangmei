package com.chuangmei.web.base;

import java.io.Serializable;

import org.apache.log4j.Logger;

/**  
 *   
 * 类名称：AbstractBaseFilter  
 * 类描述：  -== filter基类 ==-
 * 创建人：yuanqi.jing 
 * 创建时间：2015-02-04 下午13:07:51  
 * 修改备注：  
 * @version  1.0 
 *   
 */
@SuppressWarnings("serial")
public abstract class AbstractBaseFilter implements Serializable{
	
	/**
	   * @Fields logger : log4j日志对象
	  */
	public final Logger logger = Logger.getLogger(getClass());
}
