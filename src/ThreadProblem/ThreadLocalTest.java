package ThreadProblem;

/**
 * @author xiaobaobao
 * @date 2020/5/20，15:54
 */
public class ThreadLocalTest {

	public static ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
	public static ThreadLocal<Integer> threadLocal2 = new InheritableThreadLocal<>();

	public static void main(String[] args) {
		threadLocal1.set(1111);
		threadLocal2.set(2222);
		//默认继承的
		Thread thread = new MyThread();
		thread.start();
		System.out.println("main = " + threadLocal1.get());
		System.out.println("main = " + threadLocal2.get());
	}


	static class MyThread extends Thread {
		@Override
		public void run() {
			System.out.println("MyThread = " + threadLocal1.get());
			System.out.println("MyThread = " + threadLocal2.get());
		}
	}

}
