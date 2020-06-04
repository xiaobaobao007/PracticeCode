package ThreadProblem.JUC;

import java.util.concurrent.CyclicBarrier;

/**
 * @author xiaobaobao
 * @date 2020/5/11，23:34
 *
 *  相比较与CountDownLatch,更能重复利用
 */
public class CyclicBarrierDemo {

	static class TaskThread extends Thread {

		CyclicBarrier barrier;

		public TaskThread(CyclicBarrier barrier) {
			this.barrier = barrier;
		}

		@Override
		public void run() {
			try {
				System.out.println(getName() + " 到达栅栏 A");
				barrier.await();

				Thread.sleep(3000);
				System.out.println(getName() + " 到达栅栏 B");
				barrier.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		int threadNum = 5;
		CyclicBarrier barrier = new CyclicBarrier(threadNum, () -> System.out.println(Thread.currentThread().getName() + " 完成最后任务"));

		for (int i = 0; i < threadNum; i++) {
			new TaskThread(barrier).start();
		}
	}

}
