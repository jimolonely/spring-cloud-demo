package com.jimo.app2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jimo
 * @date 18-12-18 上午10:32
 */
@RestController
@RefreshScope
public class HelloController {

	@Value("${server.port}")
	private String port;

	@RequestMapping("/hello")
	public String hello() {
		return "hello, I'm app2!, port is: " + port;
	}

	@Resource
	private ConfigLoader configLoader;

	@Value("${msg:default cont}")
	private String msg;

	@RequestMapping("/getMsg")
	public String msg() {
		return configLoader.getMsg() + ", in controller: " + msg;
	}
}
