package ThreadProblem;

/**
 * @author xiaobaobao
 * @date 2020/3/29，23:21
 */
class MyThread1 implements Runnable {

	public Object object;

	public MyThread1(Object object) {
		this.object = object;
	}

	public void waitTest() {
		synchronized (object) {
			System.out.println("MyThread1 start");
			try {
				object.wait(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("MyThread1 end");
		}

	}

	public void run() {
		this.waitTest();
	}
}

class MyThread2 implements Runnable {

	public Object object;
	public Thread thread;

	public MyThread2(Object object) {
		this.object = object;
	}

	public MyThread2(Object object, Thread thread) {
		this.object = object;
		this.thread = thread;
	}

	public void waitTest() {
		synchronized (object) {
			System.out.println("MyThread2 start");
			try {
//                object.wait(6000);
				System.out.println("1 join 2");
				thread.join();
				System.out.println("1 join 2 结束");
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("MyThread2 end");
		}
	}

	public void run() {
		this.waitTest();
	}
}

public class Demo1 {
	public static void main(String[] args) throws InterruptedException {
		Object lock = new Object();
		MyThread1 myThread1 = new MyThread1(lock);
		Thread thread1 = new Thread(myThread1);
		MyThread2 myThread2 = new MyThread2(lock, thread1);

		Thread thread2 = new Thread(myThread2);
		thread1.start();
		thread2.start();


		System.out.println("主线程结束");

//        Thread.sleep(7000);
//
//        System.out.println("thread1 7秒alive ： " + thread1.isAlive());
//        System.out.println("thread2 7秒alive ： " + thread2.isAlive());

	}
}

