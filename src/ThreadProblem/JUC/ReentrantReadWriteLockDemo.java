package ThreadProblem.JUC;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xiaobaobao
 * @date 2020/6/4，21:30
 *
 * ReadLock
 *
 * WriteLock
 *
 */
public class ReentrantReadWriteLockDemo extends Thread {

	public static int i = 0;
	public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public static void main(String[] args) {
//		new ReentrantReadWriteLockDemo().start();
		for (int j = 0; j < 5; j++) {
			new Thread(() -> {
				for (; ; ) {
					try {
						lock.readLock().lock();
						Thread.sleep(2000);
						System.out.println(i);
						lock.readLock().unlock();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

	@Override
	public void run() {
		for (; ; ) {
			try {
				Thread.sleep(1000);
				lock.writeLock().lock();
				Thread.sleep(2000);
				i++;
				lock.writeLock().unlock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
