package com.jimo.aop.mine.util;

/**
 * @author jimo
 * @date 19-2-26 下午2:12
 */
public class Assert {

	public static void assertNotNull(String msg, Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException(msg);
		}
	}
}
