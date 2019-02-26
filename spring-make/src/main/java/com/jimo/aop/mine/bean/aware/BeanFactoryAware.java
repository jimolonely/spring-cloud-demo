package com.jimo.aop.mine.bean.aware;

import com.jimo.aop.mine.bean.factory.BeanFactory;

/**
 * @author jimo
 * @date 19-2-26 上午11:06
 */
public interface BeanFactoryAware extends Aware {
	/**
	 * set
	 * @author jimo
	 * @date 19-2-26 上午11:07
	 * @param beanFactory bean factory
	 */
	void setBeanFactory(BeanFactory beanFactory);
}
