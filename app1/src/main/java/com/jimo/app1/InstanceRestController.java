package com.jimo.app1;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
public class InstanceRestController {

	@Resource
	private DiscoveryClient discoveryClient;

	@RequestMapping("/service-instances/{appName}")
	public List<ServiceInstance> serviceInstancesByAppName(@PathVariable String appName) {
		return this.discoveryClient.getInstances(appName);
	}
}
