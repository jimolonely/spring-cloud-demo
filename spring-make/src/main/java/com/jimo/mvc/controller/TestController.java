package com.jimo.mvc.controller;

import com.jimo.mvc.annotation.Controller;
import com.jimo.mvc.annotation.RequestMapping;

/**
 * @author jimo
 * @date 19-2-24 上午8:34
 */
@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/nothing")
	public void nothing() {
		System.out.println("access nothing");
	}

	@RequestMapping("/getName")
	public String getName(String id) {
		return "id=" + id + ",name=Jimo";
	}
}
