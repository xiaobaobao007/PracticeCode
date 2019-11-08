package ThreadProblem;

/**
 * @author xiaobaobao
 * @date 2019/8/5 14:40
 */
public class ThreadTest {

	public static void main(String[] args) throws Exception {
//        joinTest();
//        yieldTest();
//        interruptTest();
	}

	/**
	 * 等待一个线程die后才继续执行新的任务
	 *
	 * @throws Exception
	 */
	public static void joinTest() throws Exception {
		Thread a = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println("a");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread b = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.print("b");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		a.start();
		/*立刻生效*/
		a.join();
		/*等待多少毫秒*/
//        a.join(300);
		/*等待多少毫秒和纳秒*/
//        a.join(1,1);
		b.start();
	}

	/**
	 * 当前线程进行让步
	 */
	public static void yieldTest() {
		int num = 100;
		Thread a = new Thread(() -> {
			for (int i = 0; i < num; i++) {
				System.out.print("a");
				Thread.yield();
			}
		});
		Thread b = new Thread(() -> {
			for (int i = 0; i < num; i++) {
				System.out.print("b");
			}
		});
		a.start();
		b.start();
	}

	public static void interruptTest() {
		Thread a = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.print("i=" + i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		a.start();
		System.out.println(Thread.activeCount());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		a.interrupt();

//        Thread.currentThread().interrupt();
//        System.out.println("第一次调用thread.isInterrupted()：" + Thread.currentThread().isInterrupted());
//        System.out.println("第一次调用thread.interrupted()：" + Thread.currentThread().interrupted());
//        System.out.println("第二次调用thread.interrupted()：" + Thread.currentThread().interrupted());
	}

}
