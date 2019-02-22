package com.jimo.mvc.annotation;

import java.lang.annotation.*;

/**
 * @author jimo
 * @date 19-2-22 下午5:14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {

	String value() default "";
}
