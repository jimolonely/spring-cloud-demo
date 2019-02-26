package com.jimo.aop.mine.bean.beandefinition;

import com.jimo.aop.mine.util.StringUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author jimo
 * @date 19-2-26 上午11:19
 */
public interface BeanDefinition {

	String SINGLETON = "singleton";
	String PROTOTYPE = "prototype";

	Class<?> getBeanClass();

	String getBeanName();

	String getBeanFactory();

	String getCreateBeanMethod();

	String getStaticCreateBeanMethod();

	String getBeanInitMethodName();

	String getBeanDestroyMethodName();

	String getScope();

	boolean isSingleton();

	boolean isPrototype();

	default boolean validate() {
		if (getBeanClass() == null) {
			return !StringUtil.isBlank(getBeanFactory()) || !StringUtil.isBlank(getCreateBeanMethod());
		}
		return true;
	}

	// DI

	List<?> getConstructorArg();

	Constructor<?> getConstructor();

	void setConstructor(Constructor<?> constructor);

	Method getFactoryMethod();

	void setFactoryMethod(Method factoryMethod);


	Map<String, Object> getPropertyKeyValue();

	void setPropertyKeyValue(Map<String, Object> properties);
}
