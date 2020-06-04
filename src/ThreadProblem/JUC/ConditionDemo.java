package ThreadProblem.JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaobaobao
 * @date 2020/6/2，22:05
 * <p>
 * 一把锁，可以有多个等待对象。使用和synchronied一样，必须先获取锁
 */
class ConditionDemo {

	static final Lock lock = new ReentrantLock();

	static final Condition put = lock.newCondition();
	static final Condition get = lock.newCondition();

	static int bread = 0;

	public static void main(String[] args) {
		//生产者
		new Thread(() -> {
			for (; ; ) {
				lock.lock();
				try {
					if (bread != 0) {
						put.await();
					}
					TimeUnit.SECONDS.sleep(2);
					bread++;
					get.signal();
					System.out.println("put" + bread);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}).start();

		//消费者
		new Thread(() -> {
			for (; ; ) {
				lock.lock();
				try {
					if (bread == 0) {
						get.await();
					}
					TimeUnit.SECONDS.sleep(1);
					bread--;
					put.signal();
					System.out.println("get" + bread);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}).start();
	}

}