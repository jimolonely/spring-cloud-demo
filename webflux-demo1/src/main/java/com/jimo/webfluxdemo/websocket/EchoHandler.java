package com.jimo.webfluxdemo.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

/**
 * @author jimo
 * @date 2019/2/3 18:32
 */
@Component
public class EchoHandler implements WebSocketHandler {

	@Override
	public Mono<Void> handle(WebSocketSession session) {
		return session
				.send(session.receive()
						.map(msg -> session.textMessage("ECHO->" + msg.getPayloadAsText())));
	}
}
