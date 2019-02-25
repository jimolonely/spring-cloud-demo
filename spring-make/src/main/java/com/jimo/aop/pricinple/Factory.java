package com.jimo.aop.pricinple;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author jimo
 * @date 19-2-25 上午10:15
 */
public class Factory {

	public static Base getInstance(CglibProxy proxy) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Base.class);

		enhancer.setCallback(proxy);
		return (Base) enhancer.create();
	}
}
