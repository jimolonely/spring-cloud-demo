package com.jimo.aop.mine.util;

/**
 * @author jimo
 * @date 19-2-26 上午11:23
 */
public class StringUtil {

	public static boolean isBlank(String s) {
		return s == null || "".equals(s.trim());
	}
}
