package com.jimo.router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author jimo
 * @date 18-12-18 上午10:59
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class RouterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouterApplication.class, args);
	}

//	@Bean
//	public SimpleFilter simpleFilter() {
//		return new SimpleFilter();
//	}
}

