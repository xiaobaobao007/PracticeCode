package LockTest;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaobaobao
 * @date 2020/6/2，9:33
 *
 * 解释见 {@link LockSupportTest}
 */
public class LockSupportDemo {

	public static Object lock = new Object();

	static ChangeObjectThread t1 = new ChangeObjectThread("t1");
	static ChangeObjectThread t2 = new ChangeObjectThread("t2");

	public static class ChangeObjectThread extends Thread {
		public ChangeObjectThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			System.out.println("in " + getName());
			LockSupport.park();
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("被中断了");
			}
			System.out.println(getName() + "继续执行");
		}
	}

	@Test
	public void test1() throws InterruptedException {
		t1.start();
		t2.start();
		Thread.sleep(1000L);
		LockSupport.unpark(t1);
		Thread.sleep(1000L);
		LockSupport.unpark(t2);
		t1.join();
		t2.join();
	}

	@Test
	public void test2() throws InterruptedException {
		t1.start();
		t1.interrupt();
		Thread.sleep(1000L);
		LockSupport.unpark(t1);
		t1.join();
	}

}