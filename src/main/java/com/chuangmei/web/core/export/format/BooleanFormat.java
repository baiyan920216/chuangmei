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
public class BooleanFormat {

	public String format(Object o) {
		if (null != o) {
			Long l = (Long) o;
			if (l.intValue() == 0) {
				return "否";
			} else {
				return "是";
			}
		}
		return "";
	};
}
