/**
* CopyRright @2014 SIM TECHNOLOGY ALL RIGHTS RESERVED
* @Title: Format.java
* @Package com.sim.machine.web.core.export.annotation
* @Description: TODO
* @author gang.fan
* @date 2014年12月17日 下午5:19:27
* @version V1.0
*/
package com.chuangmei.web.core.export.format;

/** 
 * @ClassName: Format 
 * @Description: TODO> 
 * @author gang.fan
 * @date 2014年12月17日 下午5:19:27 
 *  
 */
public class Format {

	public String format(Object o){
		if(null != o){
			return o.toString();
		}
		return "";
	};
}
