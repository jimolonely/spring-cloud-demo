package com.jimo.app1;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 访问app2的API，声明断路器
 *
 * @author jimo
 * @date 18-12-18 下午3:52
 */
@Service
public class App2Service {

	@Resource
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "onError")
	public String getHelloFromApp2() {
		return restTemplate.getForObject("http://app2/hello", String.class);
	}

	public String onError() {
		return "服务当前不可用，请等待";
	}
}
