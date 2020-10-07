package Collection.Queue;

import java.util.ArrayDeque;

import org.junit.Test;

/**
 * @author xiaobaobao
 * @date 2020/10/7，23:03
 */
public class ArrayDequeTest {
	@Test
	public void add() {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		for (int i = 0; i < 15; i++) {
			deque.add(i);
		}
		deque.add(666);
		deque.forEach((a) -> System.out.print(a + "_"));
		System.out.println();
		deque.removeFirst();
		deque.forEach((a) -> System.out.print(a + "_"));
	}
}
