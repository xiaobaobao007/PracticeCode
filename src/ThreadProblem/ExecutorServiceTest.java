package ThreadProblem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaobaobao
 * @date 2019/8/5 9:22
 */
public class ExecutorServiceTest {
	public static void main(String[] args) {
		moreThread();
	}

	public static void moreThread() {
		ExecutorService exe = Executors.newScheduledThreadPool(10);
		try {
			int threadNum = 0;
			for (int i = 0; i < 10; i++) {
				threadNum++;

				final int currentThreadNum = threadNum;
				exe.execute(new Runnable() {

					@Override
					public void run() {
						try {
							System.out.println("子线程[" + currentThreadNum + "]开启");
							Thread.sleep(1000 * 10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						} finally {
							System.out.println("子线程[" + currentThreadNum + "]结束");
						}
					}
				});
			}

			System.out.println("已经开启所有的子线程");
			exe.shutdown();
			System.out.println("shutdown()：启动一次顺序关闭，执行以前提交的任务，但不接受新任务。");
			while (true) {
				if (exe.isTerminated()) {
					System.out.println("所有的子线程都结束了！");
					break;
				}
				Thread.sleep(1000);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("主线程结束");
		}
	}
}
