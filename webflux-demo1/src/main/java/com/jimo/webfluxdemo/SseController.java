package com.jimo.webfluxdemo;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * SSE
 *
 * @author jimo
 * @date 2019/2/3 18:16
 */
@RestController
@RequestMapping("/sse")
public class SseController {

	@RequestMapping("/randomNumbers")
	public Flux<ServerSentEvent<Integer>> randomNumbers() {
		return Flux.interval(Duration.ofSeconds(1))
				.map(t -> Tuples.of(t, ThreadLocalRandom.current().nextInt()))
				.map(data -> ServerSentEvent.<Integer>builder()
						.event("random")
						.id(Long.toString(data.getT1()))
						.data(data.getT2())
						.build());
	}
}
