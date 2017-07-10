package org.yang.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public final class Tool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Auto-generated method stub
		// System.out.println( Tool.getStringMD5("55555") );
		// System.out.println( Tool.encryptionString("55555") );
	}

	/**
	 * 加密密码 只是简单的MD5迭代
	 * 
	 * @param algorithm
	 * @return
	 */
	public static String encryptionString(String str) {
		String ret = null;
		ret = Tool.getStringMD5(str);
		for (int i = 0; i < 5; i++) {
			ret = Tool.getStringMD5(ret);
		}
		return ret;
	}

	public static String getStringMD5(String algorithm) {
		MessageDigest md = null;
		String algorithmMD5 = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(algorithm.getBytes());
			algorithmMD5 = new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return algorithmMD5;
	}

	/**
	 * 返回uuid
	 * 
	 * @return
	 */
	public static String returnUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 格式化double
	 * 
	 * @param d
	 * @param b
	 *            保留位数
	 * @return
	 */
	public static double formatDouble(double d, int b) {
		BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);
		return bg.doubleValue();
	}

}
