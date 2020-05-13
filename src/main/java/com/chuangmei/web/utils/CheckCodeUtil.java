package com.chuangmei.web.utils;

import java.util.Random;

public class CheckCodeUtil {

	private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8',
			'9' };

	public static String createCode(int codeCount) {
		// randomCode记录随机产生的验证码
		StringBuffer randomCode = new StringBuffer();
		// 生成随机数
		Random random = new Random();

		// 随机产生codeCount个字符的验证码。
		for (int i = 0; i < codeCount; i++) {
			String strRand = String.valueOf(codeSequence[random
					.nextInt(codeSequence.length)]);
			// 将产生的四个随机数组合在一起。
			randomCode.append(strRand);
		}
		return randomCode.toString().toLowerCase();
	}
	public static void main(String[] args) {
		System.out.println(createCode(6).toLowerCase());
	}
}
