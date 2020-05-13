package ThreadProblem.AQS;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 只允许设定得线程数进入
 *
 * @author xiaobaobao
 * @date 2020/5/13，22:41
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

	public static void main(String[] args) {
		SemaphoreDemo demo = new SemaphoreDemo();
		new Thread(demo).start();
		new Thread(demo).start();
		new Thread(demo).start();
		new Thread(demo).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(demo.getI());
	}

	@Override
	public void run() {
		for (int j = 0; j < 100; j++) {
			addI();
		}
	}

}
