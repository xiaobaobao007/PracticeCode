package ThreadProblem.JUC;

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
public class LockSupportDemo {

	public static void main(String[] args) throws InterruptedException {

		Thread thread = new Thread(() -> {
			System.out.println("waiting");
			LockSupport.park();
			System.out.println("done");
		});
		thread.start();

		TimeUnit.SECONDS.sleep(1);

		LockSupport.unpark(thread);
	}

}