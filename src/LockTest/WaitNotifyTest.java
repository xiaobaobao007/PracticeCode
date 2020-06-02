package LockTest;

/**
 * @author xiaobaobao
 * @date 2020/6/2，13:32
 */
public class WaitNotifyTest {

	private static Object lock = new Object();

	public static void main(String[] args) {
		new Thread(new WaitThread()).start();
//		new Thread(new WaitThread()).start();
//		new Thread(new NotifyThread()).start();
	}

	static class WaitThread implements Runnable {
		@Override
		public void run() {
			synchronized (lock) {
				System.out.println("start wait!");
				try {
					//释放锁，等待多久再获取锁
					lock.wait(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("end wait!");
			}
		}
	}

	static class NotifyThread implements Runnable {
		@Override
		public void run() {
			synchronized (lock) {
				System.out.println("start notify!");
				lock.notify();
				System.out.println("end notify");
			}
		}
	}
}