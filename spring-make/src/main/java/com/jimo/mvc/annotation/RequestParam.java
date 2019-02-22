package com.jimo.mvc.annotation;

import java.lang.annotation.*;

/**
 * @author jimo
 * @date 19-2-22 下午5:17
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {

	String value();
}
