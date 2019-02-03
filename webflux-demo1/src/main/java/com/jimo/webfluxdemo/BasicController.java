package com.jimo.webfluxdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author jimo
 * @date 2019/2/3 18:14
 */
@RestController
public class BasicController {

	@RequestMapping("/hello")
	public Mono<String> hello() {
		return Mono.just("hello mono");
	}
}
