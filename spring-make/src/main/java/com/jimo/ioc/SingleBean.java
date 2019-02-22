package com.jimo.ioc;

/**
 * @author jimo
 * @date 19-2-22 上午10:07
 */
@Component(scope = Component.SCOPE_SINGLETON)
public class SingleBean {
	public SingleBean() {
		System.out.println("single bean constructor...");
	}
}
