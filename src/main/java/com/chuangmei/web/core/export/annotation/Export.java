package com.chuangmei.web.core.export.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
* @ClassName: Export 
* @Description: 报表导出
* @author gang.fan
* @date 2014年12月17日 上午10:41:43 
*
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
// 方法级别
public @interface Export {
	
	Column[] columns() default {};//列（含有标题、属性、格式化类）
	
	String template() default "";//模版文件名称
}
