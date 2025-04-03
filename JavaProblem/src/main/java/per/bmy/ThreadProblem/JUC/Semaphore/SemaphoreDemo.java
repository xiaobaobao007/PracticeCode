package per.bmy.ThreadProblem.JUC.Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaobaobao
 * @date 2020/5/13，22:41
 * <p>
 * 允许初始化为 多个凭证，或者0个凭证
 * <p>
 * acquire 消耗凭证，必须有剩余的凭证存在，否则阻塞等待凭证的产生
 * <p>
 * release 释放凭证
 */
public class SemaphoreDemo implements Runnable {

    private int i;
    private final Semaphore semaphore;

    public SemaphoreDemo() {
        i = 0;
        semaphore = new Semaphore(2);
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
        System.out.println(this.i++);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        for (int j = 0; j < 10; j++) {
            addI();
        }
    }

}
