package com.chuangmei.web.utils;

/**
 * 
* @ClassName: ConstantUtils 
* @Description: 常量工具类 
* @author yuanqi.jing
* @date 2015年2月4日 下午3:23:34 
*
 */
public class ConstantUtils {

	/**
	 * @Fields LOGIN_TIMEOUT : 微信公众号token 登录令牌超时时间，单位：毫秒
	 */
	public static long LOGIN_TIMEOUT =  60 * 60 * 1000;

	/**
	 * @Fields LOGIN_TOKEN_KEY_IN_REDIS : 微信公众号的access_token在缓存中的key的前缀
	 */
	public static final String ACCESS_TOKEN_KEY_PREFIX = "accessTokenKey_";



	public static final String APP_USER_SESSIONID = "app_sessionid_";

	public static final String USER_IN_SESSION = "web_logon_user";

}
