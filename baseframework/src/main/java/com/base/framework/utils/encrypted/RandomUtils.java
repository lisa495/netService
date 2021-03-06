package com.base.framework.utils.encrypted;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 随机数工具类
 *  
 */
public class RandomUtils {
	private static String tag="RandomUtils";
	public static SecureRandom getSecureRandomInstance() {
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return random;
	}
	
	/**
	 * 生成指定长度的随机数，纯数字字符串
	 */
	private static final char[] CHAR_RANDOMS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	private static final char[] CHAR_RANDOMS_NONZERO = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	public static String generateNumberString(int length) {
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (random == null) {
			return "";
		}
		StringBuffer ret = new StringBuffer();
		for (int i = 0; i < length; i++) {
			ret.append(CHAR_RANDOMS[random.nextInt(CHAR_RANDOMS.length)]);
		}
		return ret.toString();
	}
	
	/**
	 * 生成指定位数的随机数
	 */
	public static int generateNumber(int length) {
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (random == null) {
			return -1;
		}
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				ret.append(CHAR_RANDOMS_NONZERO[random.nextInt(CHAR_RANDOMS_NONZERO.length)]);
			} else {
				ret.append(CHAR_RANDOMS[random.nextInt(CHAR_RANDOMS.length)]);				
			}			
		}
		String str = ret.toString();

		Integer number = null;
		try {
			number = Integer.valueOf(str);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		if (number == null) {
			return -1;
		}
		return number;
	}
	
	/**
	 * 生成指定长度的随机字符串，数字字母组合
	 */
	private static final String POSSIBLE_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String generateComposeString(int length) {
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (random == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(POSSIBLE_CHARS.charAt(random.nextInt(POSSIBLE_CHARS
					.length())));
		}
		return sb.toString();
	}	
}
