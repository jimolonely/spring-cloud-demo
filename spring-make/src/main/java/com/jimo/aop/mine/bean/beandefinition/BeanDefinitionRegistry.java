package com.jimo.aop.mine.bean.beandefinition;

/**
 * @author jimo
 * @date 19-2-26 上午11:29
 */
public interface BeanDefinitionRegistry {

	void register(BeanDefinition db, String beanName);

	boolean containsBeanDefinition(String beanName);

	BeanDefinition getBeanDefinition(String beanName);
}
