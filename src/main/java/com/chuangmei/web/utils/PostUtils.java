package com.chuangmei.web.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

public class PostUtils {
	/**
	 * 
	 * @Title：receivePost
	 * @Description: 取post传递数据 
	 * @author shaojian.yu 
	 * @date 2015年4月2日 下午1:07:45
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static String receivePost(HttpServletRequest request)
			throws IOException, UnsupportedEncodingException {

		// 读取请求内容
		BufferedReader br = new BufferedReader(new InputStreamReader(
				request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		// 将资料解码
		String reqBody = sb.toString();
		return reqBody;
//		return URLDecoder.decode(sb.toString(), "UTF-8");
	}
}
