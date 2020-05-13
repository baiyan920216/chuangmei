package com.chuangmei.web.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;



/**
 * @author fg
 * 
 */
public class ZipUtils {

	/**
	 * 字符串压缩成byte[]
	 * 
	 * @param str
	 *            带转换的字符串
	 * @return 压缩后的byte[]
	 * @throws Exception
	 */
	public static String encodeString(String str) throws Exception {

		// 获取准备被压缩的数据的字节码
		byte[] src = URLEncoder.encode(str, "utf-8").getBytes();
		// 3:在内存中声明一个容器
		ByteArrayOutputStream destByte = new ByteArrayOutputStream();
		// ４：声明压缩的工具流，并设置压缩的目的地为destByte

		GZIPOutputStream zip = new GZIPOutputStream(destByte);

		// 5:写入数据
		zip.write(src);
		// 6:关闭压缩工具流
		zip.close();
		// System.err.println("压缩之前字节码大小：" + src.length);
		// ７：获取压缩以后数据
		byte[] dest = destByte.toByteArray();

		// System.out.println(new String(dest,"utf-8"));
		// System.err.println("压缩以后的字节码大小：" + dest.length);
		// System.out.println(str);
		// log.info("成功转换返回数据！" + DateUtil.getTimeNow());

		return new String(dest, "ISO-8859-1");
	}

	/**
	 * 解压字符串
	 * 
	 * @param byteStr
	 *            byte字符串
	 * @return 解压后的字符串
	 * @throws Exception
	 */
	public static String decodeString(String byteStr) throws Exception {
		// 转换待解压的字符串为byte[]
		byte[] dest = byteStr.getBytes("ISO-8859-1");

		// 解压
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream is = new ByteArrayInputStream(dest);
		GZIPInputStream zip1 = new GZIPInputStream(is);

		byte[] buffer = new byte[256];
		int n;
		while ((n = zip1.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		
		//返回解压后的字符串
		return URLDecoder.decode(out.toString(), "utf-8");
	}
	
	
	public static byte[] file2Byte(File file) {
		byte[] buffer = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}
}
