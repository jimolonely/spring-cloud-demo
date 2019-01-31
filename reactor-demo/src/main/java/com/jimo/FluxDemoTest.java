package com.jimo;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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


	@Test
	public void testBuffer() {
		Flux<List<Integer>> buffer = Flux.range(1, 100).buffer(20);
		buffer.subscribe(System.out::println);

		Flux<List<Long>> buffer1 = Flux.interval(Duration.of(100, ChronoUnit.MILLIS)).buffer(Duration.ofMillis(1001));
		buffer1.take(2).toStream().forEach(System.out::println);

		Flux<List<Integer>> listFlux = Flux.range(1, 10).bufferUntil(i -> i % 2 == 0);
		listFlux.subscribe(System.out::println);

		Flux<List<Integer>> listFlux1 = Flux.range(1, 10).bufferWhile(i -> i % 2 == 0);
		listFlux1.subscribe(System.out::println);
	}

	@Test
	public void testFilter() {
		Flux<Integer> filter = Flux.range(0, 10).filter(i -> i % 2 == 0);
		filter.subscribe(System.out::println);
	}

	@Test
	public void testWindow() {
		Flux<Flux<Integer>> window = Flux.range(1, 100).window(20);
		window.subscribe(System.out::println);

		Flux<Flux<Long>> window1 = Flux.interval(Duration.ofMillis(100)).window(Duration.ofMillis(1001));
		window1.take(2).toStream().forEach(System.out::println);

	}

	@Test
	public void testZipWith() {
		Flux<Tuple2<String, String>> tuple2Flux = Flux.just("a", "b").zipWith(Flux.just("c", "d"));
		tuple2Flux.subscribe(System.out::println);

		Flux<String> stringFlux = Flux.just("a", "b").zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s-%s", s1, s2));
		stringFlux.subscribe(System.out::println);
	}

	@Test
	public void testTake() {
		Flux<Integer> take = Flux.range(1, 1000).take(10);
		take.subscribe(System.out::println);

		Flux<Integer> integerFlux = Flux.range(1, 1000).takeLast(10);
		integerFlux.subscribe(System.out::println);

		Flux.range(1, 1000).takeWhile(i -> i < 10).subscribe(System.out::println);

		Flux.range(1, 1000).takeUntil(i -> i == 10).subscribe(System.out::println);
	}

	@Test
	public void testReduce() {
		// 累积成一个Mono
		Mono<Integer> reduce = Flux.range(1, 100).reduce((x, y) -> x + y);
		reduce.subscribe(System.out::println);

		Mono<Integer> integerMono = Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y);
		integerMono.subscribe(System.out::println);
	}

	@Test
	public void testMerge() {
		Flux.merge(Flux.interval(Duration.ofMillis(100)).take(5), Flux.interval(Duration.ofMillis(50)).take(5))
				.toStream().forEach(System.out::println);

		Flux.mergeSequential(Flux.interval(Duration.ofMillis(100)).take(5), Flux.interval(Duration.ofMillis(50)).take(5))
				.toStream().forEach(System.out::println);
	}

	@Test
	public void testFlat() {
		Flux.just(5, 10).flatMap(x -> Flux.interval(Duration.ofMillis(x * 10)).take(5))
				.toStream().forEach(System.out::println);

		Flux.just(5, 10).flatMapSequential(x -> Flux.interval(Duration.ofMillis(x * 10)).take(5))
				.toStream().forEach(System.out::println);
	}

	@Test
	public void testConcat() {
		Flux.just(5, 10).concatMap(x -> Flux.interval(Duration.ofMillis(x * 10)).take(x))
				.toStream().forEach(System.out::println);
	}

	@Test
	public void testCombineLatest() {
		Flux.combineLatest(Arrays::toString, Flux.interval(Duration.ofMillis(100)).take(5),
				Flux.interval(Duration.ofMillis(50)).take(5))
				.toStream().forEach(System.out::println);
	}
}


