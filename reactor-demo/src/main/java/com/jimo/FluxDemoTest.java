package com.jimo;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author jimo
 * @date 19-1-29 上午8:55
 */
public class FluxDemoTest {


	@Test
	public void testStatic() {
		Flux<String> just = Flux.just("hello", "world");
		just.subscribe(System.out::println);

		Flux<Integer> integerFlux = Flux.fromArray(new Integer[]{1, 2, 3});
		integerFlux.subscribe(System.out::println);

		Flux<Object> empty = Flux.empty();
		empty.subscribe(System.out::println);

		Flux<Integer> range = Flux.range(1, 10);
		range.subscribe(System.out::println);

		Flux<Long> interval = Flux.interval(Duration.of(10, ChronoUnit.SECONDS));
		interval.subscribe(System.out::println);
	}

	@Test
	public void testGenerate() {
		Flux<Object> hello = Flux.generate(sink -> {
			sink.next("hello");
			sink.complete();
		});
		hello.subscribe(System.out::println);

		Random random = new Random();
		Flux<Object> generate = Flux.generate(ArrayList::new, (list, sink) -> {
			int value = random.nextInt(100);
			list.add(value);
			sink.next(value);
			if (list.size() == 10) {
				sink.complete();
			}
			return list;
		});
		generate.subscribe(System.out::println);
	}

	@Test
	public void testCreate() {
		Flux<Object> flux = Flux.create(sink -> {
			for (int i = 0; i < 10; i++) {
				sink.next(i);
			}
			sink.complete();
		});
		flux.subscribe(System.out::println);

		// exception
		Flux.generate(sink -> {
			for (int i = 0; i < 5; i++) {
				sink.next(i);
			}
			sink.complete();
		}).subscribe(System.out::println);
	}


}


