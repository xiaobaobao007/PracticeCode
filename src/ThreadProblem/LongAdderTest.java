package ThreadProblem;

import sun.management.counter.LongArrayCounter;
import sun.management.counter.perf.PerfLongArrayCounter;

import java.util.concurrent.atomic.LongAdder;

/**
 * LongAdder在AtomicLong的基础上将单点的更新压力分散到各个节点，
 * 在低并发的时候通过对base的直接更新可以很好的保障和AtomicLong的性能基本保持一致，而在高并发的时候通过分散提高了性能。
 * <p>
 * 缺点:
 * LongAdder在统计的时候如果有并发更新，可能导致统计的数据有误差。
 *
 * @author xiaobaobao
 * @date 2020/5/3，11:33
 */
public class LongAdderTest {
	public static void main(String[] args) {
		LongAdder add = new LongAdder();
		add.increment();
		System.out.println(add.sum());
		System.out.println(add.sumThenReset());
		System.out.println(add.sum());
	}
}
