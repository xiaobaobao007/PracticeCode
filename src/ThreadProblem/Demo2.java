package ThreadProblem;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaobaobao
 * @date 2020/3/30，21:38
 */
public class Demo2 {

	private int a;

	public int get() {
		return a;
	}

	public void add1() {
		this.a++;
	}

	public synchronized void add2() {
		this.a++;
	}

	public static void main(String[] args) throws InterruptedException {
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		lock.lock();
//		Demo2 demo2 = new Demo2();
//		CountDownLatch count = new CountDownLatch(2);
//		Thread thread = new Thread(() -> {
//			for (int i = 0; i < 10000; i++) {
//				demo2.add1();
//			}
//			count.countDown();
//		});
//		Thread thread1 = new Thread(() -> {
//			for (int i = 0; i < 10000; i++) {
//				demo2.add2();
//			}
//			count.countDown();
//		});
//		thread.start();
//		thread1.start();
//		count.await();
//		System.out.println(demo2.get());
	}
}

//public class Lock {
//	boolean isLocked = false;
//	Thread lockedBy = null;
//	int lockedCount = 0;
//	public synchronized void lock() throws InterruptedException {
//		Thread thread = Thread.currentThread();
//		while (isLocked && lockedBy != thread) {wait();}
//		isLocked = true;
//		lockedCount++;
//		lockedBy = thread;
//	}
//	public synchronized void unlock() {
//		if (Thread.currentThread() == this.lockedBy) {
//			lockedCount--;
//			if (lockedCount == 0) {
//				isLocked = false;
//				notify();
//			}
//		}
//	}
//}

