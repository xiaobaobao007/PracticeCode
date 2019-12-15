package Java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author xiaobaobao
 * @date 2019/9/3，15:49
 */
public class StreamTest {

	/**
	 * stream只能使用一次
	 */
	@Test
	public void demo1() {
		List<String> list = new ArrayList<>(Arrays.asList("one", "two"));
		Stream<String> stream = list.stream();
		stream.forEach(System.out::println);
		stream.forEach(System.out::println);
	}

	/**
	 * stream 复用的正确方法
	 */
	@Test
	public void demo2() {
		Supplier<List<String>> supplier = () -> new ArrayList<>(Arrays.asList("one", "two"));
		Stream<String> stream1 = supplier.get().stream();
		stream1.forEach(System.out::println);
		Stream<String> stream2 = supplier.get().stream();
		stream2.forEach(System.out::println);
	}

	@Test
	public void demo4() {
		List<String> l = new ArrayList(Arrays.asList("1", "2", "3", "4"));
		class State {
			boolean s;
		}
		final State state = new State();
		Stream<String> sl = l.stream().map(e -> {
			if (state.s) return "OK";
			else {
				state.s = true;
				return e;
			}
		});
		sl.forEach(System.out::println);
	}

}
