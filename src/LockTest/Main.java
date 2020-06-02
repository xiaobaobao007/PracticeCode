package LockTest;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaobaobao
 * @date 2019/11/29，9:57
 */
public class Main {

	static int _1M = 1024 * 1024;

	public static void main(String[] args) {
		ReentrantLock r = new ReentrantLock();
		Condition condition = r.newCondition();
//		condition.
//
//		System.out.println(r.getHoldCount());
//		r.lock();
//		System.out.println(r.getHoldCount());
//		r.lock();
//		System.out.println(r.getHoldCount());
//		r.unlock();
//		System.out.println(r.getHoldCount());
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			new Thread(() -> {
				for (int i = 1; i < 100; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					byte[] bytes = new byte[i * _1M];
				}
			});
		}
	}
}
