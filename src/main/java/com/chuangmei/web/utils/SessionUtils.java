package com.chuangmei.web.utils;

import com.chuangmei.web.entity.User;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**  
*   
* 类名称：SessionUtils  
* 类描述：  -== Session工具类 ==-
* 创建人：yuanqi.jing  
* 创建时间：2015-2-4 下午3:59:11  
* 修改备注：  
* @version  1.0 
*   
*/ 
public class SessionUtils {
	private static SessionUtils utils = null;
	
	private SessionUtils() {
		init();
	}
	/**
	 * 初始化需要执行的代码 
	 */
	public void init() {
		// todo
	}

	/**
	 * 重置该工具类
	 */
	public void reset() {
		init();
	}

	/**
	 * 获取该单例工具类实例
	 * @return SessionUtils
	 */
	public static SessionUtils getInstance() {
		if (utils == null) {
			create();
		}
		return utils;
	}

	/**
	 * 线程安全的创建实例，防止并发多次创建
	 */
	private static synchronized void create() {
		if (utils == null) {
			utils = new SessionUtils();
		}
	}
	 
	
	/**
	 * 获取登录的用户对象
	 * @param session
	 * @return User
	 */
	public User getUserInSession(HttpSession session) {
		return (User) session.getAttribute(ConstantUtils.USER_IN_SESSION);
	}
	
	/**
	 * 将登录用户对象放入session
	 * @param session
	 * @return User
	 */
	public void putUserInSession(User user, HttpSession session) {
		session.setAttribute(ConstantUtils.USER_IN_SESSION, user);
	}
}
