package ThreadProblem;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

/**
 * @author xiaobaobao
 * @date 2020/5/20，15:54
 */
public class ThreadLocalTest {

	/**
	 * get删除null expungeStaleEntry
	 * 向后搜索只要不为空，重置，遇到不在位置的重新计算。
	 * <p>
	 * set删除null replaceStaleEntry
	 */
	public static ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
	public static ThreadLocal<Integer> threadLocal2 = new InheritableThreadLocal<>();

	public static FastThreadLocal<Integer> fastThreadLocal = new FastThreadLocal<>();
	public static Thread thread = new FastThreadLocalThread();

	public static void main(String[] args) {
		threadLocal1.set(1111);
		threadLocal2.set(2222);
		//默认继承的
		Thread thread = new MyThread();
		thread.start();
	}

	static class MyThread extends Thread {
		@Override
		public void run() {
			System.out.println("MyThread = " + threadLocal1.get());
			System.out.println("MyThread = " + threadLocal2.get());
		}
	}

}
