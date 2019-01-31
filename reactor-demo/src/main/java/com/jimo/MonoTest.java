package com.jimo;

import org.junit.Test;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author jimo
 * @date 19-1-30 下午7:13
 */
public class MonoTest {

	@Test
	public void testMono() {
		Mono<String> stringMono = Mono.fromSupplier(() -> "hello");
		stringMono.subscribe(System.out::println);

		Mono<String> hello2 = Mono.justOrEmpty(Optional.of("hello2"));
		hello2.subscribe(System.out::println);

		Mono<Object> hello3 = Mono.create(sink -> sink.success("hello3"));
		hello3.subscribe(System.out::println);
	}
}
