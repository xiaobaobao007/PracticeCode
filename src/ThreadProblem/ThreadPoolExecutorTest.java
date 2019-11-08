package ThreadProblem;

/**
 * @author xiaobaobao
 * @date 2019/8/6 14:41
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorTest {
	public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newFixedThreadPool(2);
//        ExecutorService threadPool = Executors.newCachedThreadPool();
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		for (int i = 1; i < 5; i++) {
			final int taskID = i;
			threadPool.execute(() -> {
				for (int j = 1; j < 5; j++) {
					try {
						Thread.sleep(1000);// 为了测试出效果，让每次任务执行都需要一定时间
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("第" + taskID + "次任务的第" + j + "次执行");
				}
			});
		}
		threadPool.shutdown();// 任务执行完毕，关闭线程池
	}
}
