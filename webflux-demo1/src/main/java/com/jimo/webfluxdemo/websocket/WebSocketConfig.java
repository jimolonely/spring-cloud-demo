package com.jimo.webfluxdemo.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.HandlerMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jimo
 * @date 2019/2/3 18:35
 */
@Configuration
public class WebSocketConfig {

	@Bean
	@Autowired
	public HandlerMapping webSocketMapping(final EchoHandler echoHandler) {
		Map<String, WebSocketHandler> map = new HashMap<>(1);
		map.put("/echo", echoHandler);

		final SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
		mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
		mapping.setUrlMap(map);
		return mapping;
	}

	@Bean
	public WebSocketHandlerAdapter handlerAdapter() {
		return new WebSocketHandlerAdapter();
	}
}
