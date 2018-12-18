package com.jimo.app2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 一个统一的配置加载类
 * @author jimo
 * @date 18-12-18 下午5:05
 */
@Component
@RefreshScope
public class ConfigLoader {

	@Value("${msg:hello default}")
	private String msg;

	public String getMsg() {
		return msg;
	}
}
