package AStar.cl;

import org.junit.Test;

/**
 * <pre>
 *
 * 			A*算法    执行100000次
 *
 *       448161900    新十进制算法
 *       412504100    新二进制算法
 *       659818600    传统的算法
 *
 * </pre>
 */
public class Main {

	@Test
	public void main() {
		int times = 100000;
		long start = System.nanoTime();
		for (int i = 0; i < times; i++) {
			new AStar(0, 0, AStar.NODES[0].length - 1, AStar.NODES.length - 1).main();
		}
		System.out.println(System.nanoTime() - start);

		start = System.nanoTime();
		for (int i = 0; i < times; i++) {
			new AStarB(0, 0, AStar.NODES[0].length - 1, AStar.NODES.length - 1).main();
		}
		System.out.println(System.nanoTime() - start);

		start = System.nanoTime();
		for (int i = 0; i < times; i++) {
			AStarNew.main();
		}
		System.out.println(System.nanoTime() - start);
	}

}