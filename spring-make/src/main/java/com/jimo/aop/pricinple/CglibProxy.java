package com.jimo.aop.pricinple;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author jimo
 * @date 19-2-25 上午10:12
 */
public class CglibProxy implements MethodInterceptor {

	@Override
	public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("before....");
		proxy.invokeSuper(object, args);
		System.out.println("After....");
		return null;
	}
}
