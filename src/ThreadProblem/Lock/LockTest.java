package ThreadProblem.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaobaobao
 * @date 2020/5/26，21:46
 */
public class LockTest {

	public void test() throws Exception {
		final Lock lock = new ReentrantLock();
		lock.lock();

		Thread t1 = new Thread(() -> {
//			lock.lock();
//			System.out.println(Thread.currentThread().getName() + " interrupted.");

			try {
				lock.lockInterruptibly();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " interrupted.");
				e.printStackTrace();
			}

		}, "child thread -1");

		t1.start();
		Thread.sleep(1000);

		t1.interrupt();

		Thread.sleep(1000000);
	}

	public static void main(String[] args) throws Exception {
		new LockTest().test();
	}
}
