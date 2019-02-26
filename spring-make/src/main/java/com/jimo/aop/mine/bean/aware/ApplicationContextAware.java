package com.jimo.aop.mine.bean.aware;

import com.jimo.aop.mine.context.app.ApplicationContext;

/**
 * @author jimo
 * @date 19-2-26 上午11:09
 */
public interface ApplicationContextAware extends Aware {

	/**
	 * set
	 * @author jimo
	 * @date 19-2-26 上午11:11
	 * @param applicationContext context
	 */
	void setApplicationContext(ApplicationContext applicationContext);
}
