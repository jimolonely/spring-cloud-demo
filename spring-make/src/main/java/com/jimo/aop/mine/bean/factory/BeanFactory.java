package com.jimo.aop.mine.bean.factory;

import com.jimo.aop.mine.bean.postprocessor.AopPostProcessor;

import java.util.Map;

/**
 * @author jimo
 * @date 19-2-26 上午11:09
 */
public interface BeanFactory {

	Object getBean(String beanName) throws Exception;

	void registerBeanPostProcessor(AopPostProcessor processor);

	String[] getBeanNameForType(Class<?> cls);

	Map<String, Object> getBeanForType(Class<?> cls);

	Class getType(String beanName);
}
