package com.jimo.app1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author jimo
 * @date 18-12-18 下午3:15
 */
@RestController
public class LoadBalanceController {

	@Resource
	private RestTemplate restTemplate;

	@RequestMapping("/getHello")
	public String getHelloFromApp2() {
		return restTemplate.getForObject("http://app2/hello", String.class);
	}
}
