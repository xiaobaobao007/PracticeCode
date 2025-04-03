package per.bmy.ThreadProblem.ThreadPoool;

import java.util.concurrent.*;
import org.junit.Test;

/**
 * @author xiaobaobao
 * @date 2020/5/25，21:12
 */
public class PoolTest {

    /**
     * 固定大小
     */
    @Test
    public void newFixedThreadPoolTest() {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Thread t1 = new MyThread("1");
        Thread t2 = new MyThread("2");
        Thread t3 = new MyThread("3");
        Thread t4 = new MyThread("4");
        Thread t5 = new MyThread("5");

        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);

        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单线程
     */
    @Test
    public void newSingleThreadExecutor() {
        ExecutorService pool = Executors.newSingleThreadExecutor();

        Thread t1 = new MyThread("1");
        Thread t2 = new MyThread("2");
        Thread t3 = new MyThread("3");
        Thread t4 = new MyThread("4");
        Thread t5 = new MyThread("5");

        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);

        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    @Test
    public void newCachedThreadPool() {
        ExecutorService pool = Executors.newCachedThreadPool();

        Thread t1 = new MyThread("1");
        Thread t2 = new MyThread("2");
        Thread t3 = new MyThread("3");
        Thread t4 = new MyThread("4");
        Thread t5 = new MyThread("5");

        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);

        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    @Test
    public void ScheduledThreadPoolExecutor() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

        Thread t1 = new MyThread("1");
        Thread t2 = new MyThread("2");
        Thread t3 = new MyThread("3");
        Thread t4 = new MyThread("4");
        Thread t5 = new MyThread("5");

        pool.schedule(t1, 5, TimeUnit.SECONDS);
        pool.schedule(t2, 7, TimeUnit.SECONDS);
//		pool.schedule(t3, 15, TimeUnit.SECONDS);
//		pool.schedule(t4, 20, TimeUnit.SECONDS);
//		pool.schedule(t5, 25, TimeUnit.SECONDS);

        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ScheduledThreadPoolExecutor_scheduleAtFixedRate() {

        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(2);

        Thread t1 = new MyThread() {
            @Override
            public void run() {
                try {
                    System.out.println(System.currentTimeMillis() / 1000);
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

//		pool.scheduleAtFixedRate(t1, 0, 2, TimeUnit.SECONDS);
        pool.scheduleWithFixedDelay(t1, 0, 2, TimeUnit.SECONDS);

        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class MyThread extends Thread {

        private String name;

        public MyThread(String name) {
            this.name = name;
        }

        public MyThread() {

        }

        @Override
        public void run() {
            int i = 3;
            while (i-- > 0) {
                System.out.println(name + Thread.currentThread().getName() + "正在执行。。。" + i + ":" + System.currentTimeMillis() / 1000);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
