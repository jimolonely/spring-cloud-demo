package com.jimo.aop.mine.bean.postprocessor;

/**
 * @author jimo
 * @date 19-2-26 上午11:15
 */
public interface AopPostProcessor extends BeanPostProcessor {

	Object postProcessWeaving(Object bean, String beanName) throws Exception;
}
