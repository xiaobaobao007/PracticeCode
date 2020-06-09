package ThreadProblem.JUC;

import java.util.concurrent.Semaphore;

/**
 * @author xiaobaobao
 * @date 2020/5/13，22:41
 *
 *  只允许设定得线程数进入,设定1时候即保证线程安全
 */
public class SemaphoreDemo implements Runnable {

	private int i;
	private Semaphore semaphore;

	public SemaphoreDemo() {
		i = 0;
		semaphore = new Semaphore(1);
	}

	public int getI() {
		return i;
	}

	public void addI() {
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.i++;
		semaphore.release();
	}

	public static void main(String[] args) throws InterruptedException {
		SemaphoreDemo demo = new SemaphoreDemo();
		Thread thread1 = new Thread(demo);
		Thread thread2 = new Thread(demo);
		Thread thread3 = new Thread(demo);
		Thread thread4 = new Thread(demo);

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();

		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();

		System.out.println(demo.getI());
	}

	@Override
	public void run() {
		for (int j = 0; j < 100000; j++) {
			addI();
		}
	}

}