package com.chuangmei.web.filter;

import com.chuangmei.web.base.AbstractBaseFilter;
import com.chuangmei.web.entity.User;
import com.chuangmei.web.utils.SessionUtils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录过滤器
 */
public class LoginFilter extends AbstractBaseFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5848533774295974270L;
	

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (!(servletRequest instanceof HttpServletRequest)
				|| !(servletResponse instanceof HttpServletResponse)) {
			throw new ServletException(
					"OncePerRequestFilter just supports HTTP requests");
		}
		
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		HttpSession session = httpRequest.getSession(true);
		
		// 项目根路径
		String projPath = httpRequest.getContextPath();
		// 请求路径
		String url = httpRequest.getRequestURI();

		User user = SessionUtils.getInstance()
				.getUserInSession(session);

		// 已登录 或 请求路径为除jsp文件以外资源文件路径 或 请求路径是白名单，放行
		if (user != null ) {
//			this.logger.debug("--pass--放行请求，[url:" + url + "]");
			filterChain.doFilter(httpRequest, httpResponse);
			
			// 重新设置超时时间
			//mu.resetLoginTimeOut(httpRequest.getSession());
			
		} else {// 未登录 并且 请求地址不是白名单
			String returnUrl = projPath + "/toLogin";
//			this.logger.debug("--XXX--拦截请求--XXX--，[returnUrl:" + returnUrl + "]");
			httpRequest.setCharacterEncoding("UTF-8");
			httpResponse.setContentType("text/html; charset=UTF-8"); // 转码
			httpResponse
					.getWriter()
					.println(
							"<script language=\"javascript\">window.location.href=\""
									+ returnUrl
									+ "\";</script>");
			return;
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}
