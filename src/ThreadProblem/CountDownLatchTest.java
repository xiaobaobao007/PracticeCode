package ThreadProblem;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 当所有进程结束后进行操作
 *
 * @author xiaobaobao
 * @date 2019/7/6 15:20
 */
public class CountDownLatchTest implements Runnable {

	static final CountDownLatchTest me = new CountDownLatchTest();

	static final CountDownLatch latch = new CountDownLatch(10);

	@Override
	public void run() {
		// 模拟检查任务
		try {
			Thread.sleep(new Random().nextInt(10) * 1000);
			System.out.println("check complete");

			//计数减一
			latch.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			exec.submit(me);
		}

		// 等待检查
		latch.await();

		// 发射火箭
		System.out.println("Fire!");
		// 关闭线程池
		exec.shutdown();
	}
}