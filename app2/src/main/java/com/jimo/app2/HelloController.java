package com.jimo.app2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jimo
 * @date 18-12-18 上午10:32
 */
@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String hello() {
		return "hello, I'm app2!";
	}
}
