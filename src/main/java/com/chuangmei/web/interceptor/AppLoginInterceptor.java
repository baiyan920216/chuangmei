package com.chuangmei.web.interceptor;

import com.chuangmei.web.core.jdbc.common.MessageModel;
import com.chuangmei.web.entity.User;
import com.chuangmei.web.utils.ConstantUtils;
import com.chuangmei.web.utils.MemcachedUtil;
import com.chuangmei.web.utils.StringUtil;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;

public class AppLoginInterceptor extends HandlerInterceptorAdapter {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String actionCode = request.getParameter("action_code");
		String sessionId = request.getParameter("sessionId");
		if(StringUtil.checkNotNull(actionCode)){
			if(actionCode.equals("A01")||actionCode.equals("A02")||actionCode.equals("A03")||actionCode.equals("T07")||actionCode.equals("B05")){
				return super.preHandle(request, response, handler);
			}
			User appUser = (User) MemcachedUtil.get(ConstantUtils.APP_USER_SESSIONID+sessionId);
			if(appUser!=null){
				return super.preHandle(request, response, handler);
			}
		}
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		MessageModel msg = new MessageModel();
		msg.initCode("499", "未登陆");
		String result = new Gson().toJson(msg);
		out.print(result);
		out.flush();
		return false;
	}
	
}
