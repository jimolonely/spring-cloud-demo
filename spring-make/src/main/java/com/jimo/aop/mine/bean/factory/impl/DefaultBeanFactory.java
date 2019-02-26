package com.jimo.aop.mine.bean.factory.impl;

import com.jimo.aop.mine.bean.beandefinition.BeanDefinition;
import com.jimo.aop.mine.bean.beandefinition.BeanDefinitionRegistry;
import com.jimo.aop.mine.bean.factory.BeanFactory;
import com.jimo.aop.mine.bean.postprocessor.AopPostProcessor;
import com.jimo.aop.mine.util.Assert;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jimo
 * @date 19-2-26 下午2:06
 */
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry, Closeable {

	private Map<String, BeanDefinition> bdMap = new ConcurrentHashMap<>();
	private Map<String, Object> beanMap = new ConcurrentHashMap<>();

	private ThreadLocal<Set<String>> initialedBeans = new ThreadLocal<>();

	private List<AopPostProcessor> aopPostProcessors = new ArrayList<>();

	@Override
	public void register(BeanDefinition db, String beanName) {
		Assert.assertNotNull("", beanName);
		Assert.assertNotNull("", db);

		if (bdMap.containsKey(beanName)) {
			System.out.println(beanName + " 已经存在");
		}
		if (!db.validate()) {
			System.out.println("BeanDefinition not valid");
			return;
		}
		bdMap.put(beanName, db);
	}

	@Override
	public boolean containsBeanDefinition(String beanName) {
		return bdMap.containsKey(beanName);
	}

	@Override
	public BeanDefinition getBeanDefinition(String beanName) {
		return bdMap.getOrDefault(beanName, null);
	}

	@Override
	public Object getBean(String beanName) throws Exception {
		// TODO
		return null;
	}

	@Override
	public void registerBeanPostProcessor(AopPostProcessor processor) {
		aopPostProcessors.add(processor);
	}

	@Override
	public String[] getBeanNameForType(Class<?> cls) {
		return new String[0];
	}

	@Override
	public Map<String, Object> getBeanForType(Class<?> cls) {
		return null;
	}

	@Override
	public Class getType(String beanName) {
		BeanDefinition o = (BeanDefinition) beanMap.get(beanName);
		return o.getBeanClass();
	}

	@Override
	public void close() throws IOException {

	}
}
