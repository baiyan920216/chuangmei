package com.chuangmei.web.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Util {
	
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
public static void main(String[] args) {
	String a = SHA1Util.SHA1("jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VMemybuM1VlPr0EQXTYG4DNgSu3qlTUaVTkEOeRkfvwKFvI3xo1g7-sAO97phde6FA&noncestr=B9JXBBAMJMVUSHFP&timestamp=1431331673&url=http://localhost/appContent?cid=3&forwardid=1000003");
	System.out.println(a);
}
}
