package com.jimo.mvc.annotation;

import java.lang.annotation.*;

/**
 * @author jimo
 * @date 19-2-22 下午5:15
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

	String value() default "";
}
