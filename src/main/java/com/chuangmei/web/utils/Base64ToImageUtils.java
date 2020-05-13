package com.chuangmei.web.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

public class Base64ToImageUtils {
	public static boolean base64ToImage(String base64, String dir,
			String saveFileName) throws IOException {// 对字节数组字符串进行Base64解码并生成图片
		if (base64 == null) { // 图像数据为空
			return false;
		}
		// BASE64Decoder decoder = new BASE64Decoder();
		// Base64解码
		String ss = base64.substring(base64.indexOf(",") + 1);

		byte[] bytes = Base64.decodeBase64(ss);
		for (int i = 0; i < bytes.length; ++i) {
			if (bytes[i] < 0) {// 调整异常数据
				bytes[i] += 256;
			}
		}
		// 目录
		File dirFile = new File(dir);

		// 如果目录不存在，创建目录
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		// 文件保存路径+文件名.扩展名
		String filePath = dir + File.separator + saveFileName;

		// 生成jpeg图片
		OutputStream out = new FileOutputStream(filePath);
		out.write(bytes);
		out.flush();
		out.close();
		return true;
	}

	// 加密
	public static String getBase64(String str) {
		byte[] b = null;
		String s = null;
		try {
			b = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			s = new String(Base64.encodeBase64Chunked(b));
		}
		return s.replace("\n", "").replace("\r", "");
	}

	// 解密
	public static String getFromBase64(String s) {
		byte[] b = null;
		String result = null;
		if (s != null) {
			try {
				b = Base64.decodeBase64(s);
				result = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
