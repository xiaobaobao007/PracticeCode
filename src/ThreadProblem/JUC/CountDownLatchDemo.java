package ThreadProblem.JUC;

import java.util.concurrent.CountDownLatch;

/**
 * @author xiaobaobao
 * @date 2019/7/6 15:20
 * <p>
 * 一次性，相应数量down后才唤醒await
 */
public class CountDownLatchDemo {

	static final CountDownLatch latch = new CountDownLatch(10);

	static int i = 0;

	public static void main(String[] args) throws InterruptedException {

		new Thread(() -> {
			for (; ; ) {
				i++;
				latch.countDown();
			}
		}).start();

		latch.await();

		System.out.println(i);
	}
}