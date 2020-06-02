package LockTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaobaobao
 * @date 2020/6/2，13:58
 * <p>
 * 每个线程都有一个凭证permit(0和1，默认0)
 * <p>
 * park：
 * permit=0，阻塞等待被唤醒；
 * permit=1，直接设置0并返回。
 * <p>
 * unpark：
 * permit=0，尝试唤醒线程并设置1；
 * permit=1，直接返回。
 */
public class LockSupportTest {

	static int TIMES = 1;

	public static void main(String[] args) {
		Thread parkThread = new Thread(new ParkThread());
		parkThread.start();
		for (int i = 0; i < TIMES; i++) {
			System.out.println("开始线程唤醒--");
			LockSupport.unpark(parkThread);
			System.out.println("结束线程唤醒--");
		}
	}

	static class ParkThread implements Runnable {

		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < TIMES; i++) {
				System.out.println("开始线程阻塞");
				LockSupport.park();
				System.out.println("结束线程阻塞");
			}
		}
	}
}