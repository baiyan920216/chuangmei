package com.chuangmei.web.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


public class HttpAgent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String request(List<NameValuePair> params, String url)
			throws ClientProtocolException, IOException {
		HttpClient mClient;
		HttpPost mPost;
		mClient = new DefaultHttpClient();
		mPost = new HttpPost(url);
		HttpEntity entity;
		entity = new org.apache.http.client.entity.UrlEncodedFormEntity(params,
				HTTP.UTF_8);
		mPost.setEntity(entity);
		mPost.addHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");
		mClient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
		mClient.getParams()
				.setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
		HttpResponse response = mClient.execute(mPost);
		if (response.getStatusLine().getStatusCode() == 200) {
			return EntityUtils.toString(response.getEntity());
			// URLDecoder.decode(
			// EntityUtils.toString(response.getEntity(), "UTF-8"),
			// "UTF-8");
		} else {
			return "-"
					+ String.valueOf(response.getStatusLine().getStatusCode());
			// 返回错误代码的负值
		}
	}

	public static String sendRequest(String urlStr,String rquestXML) {
		
		URL url = null;
		HttpURLConnection conn = null;
		BufferedWriter writer = null;
		BufferedReader in = null;
		String result = null;
		try {
			StringBuffer response = new StringBuffer();
			url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-type", "text/html");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("contentType", "UTF-8");
			conn.setRequestProperty("Charset", "UTF-8");
			writer = new BufferedWriter(new OutputStreamWriter(
					conn.getOutputStream(), "UTF-8"));
			writer.write(rquestXML);
			writer.flush();
			writer.close();
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			for (String line = null; (line = in.readLine()) != null;) {
				response.append(line + "\n");
			}
			in.close();
			result = response.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
