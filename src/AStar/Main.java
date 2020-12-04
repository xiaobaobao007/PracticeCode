package AStar;

import org.junit.Test;

/**
 * <pre>
 *
 * 			A*算法    执行100000次
 *
 *        842037000    新二进制算法
 *       1397877700    传统的算法
 *
 * </pre>
 */
public class Main {

	@Test
	public void main() {
		int times = 100000;
		long start = System.nanoTime();
		for (int i = 0; i < times; i++) {
			AStarNew.main();
		}
		System.out.println(System.nanoTime() - start);

		start = System.nanoTime();
		for (int i = 0; i < times; i++) {
			AStar.main();
		}
		System.out.println(System.nanoTime() - start);
	}

}