package Collection.Queue;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.apache.commons.lang.math.RandomUtils;

/**
 * @author xiaobaobao
 * @date 2020/10/5，17:37
 */
public class PriorityQueueTest {
	public static void main(String[] args) {
		PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a));
		for (int i = 1; i <= 10; i++) {
			queue.add(RandomUtils.nextInt(100));
		}
		System.out.println(queue);
		//堆顶是什么
		System.out.println(queue.peek());
		System.out.println(queue);
		//弹出堆顶
		System.out.println(queue.poll());
		System.out.println(queue);
	}
}
