package com.jimo.app1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jimo
 * @date 18-12-18 上午10:17
 */
@RestController
@RefreshScope
public class InstanceRestController {

	@Resource
	private DiscoveryClient discoveryClient;

	@RequestMapping("/service-instances/{appName}")
	public List<ServiceInstance> serviceInstancesByAppName(@PathVariable String appName) {
		return this.discoveryClient.getInstances(appName);
	}

	@Value("${msg:default msg app1}")
	private String msg;

	@RequestMapping("/getMsg")
	public String msg() {
		return  "app1 msg: " + msg;
	}
}
