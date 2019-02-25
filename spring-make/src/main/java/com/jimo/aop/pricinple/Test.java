package com.jimo.aop.pricinple;

/**
 * @author jimo
 * @date 19-2-25 上午10:17
 */
public class Test {

	public static void main(String[] args) {
		CglibProxy cglibProxy = new CglibProxy();
		Base base = Factory.getInstance(cglibProxy);
		base.add();
	}
}
