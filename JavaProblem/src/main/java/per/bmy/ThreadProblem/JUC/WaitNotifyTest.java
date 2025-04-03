package per.bmy.ThreadProblem.JUC;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaobaobao
 * @date 2020/6/2，13:32
 * <p>
 * 必须获取对应的监视器才能操作，替代品{@link ConditionDemo}
 */
public class WaitNotifyTest {

    static final Object put = new Object();
    static final Object get = new Object();

    static int bread = 0;

    public static void main(String[] args) {
        //生产者
        new Thread(() -> {
            for (; ; ) {
                try {
                    if (bread != 0) {
                        synchronized (put) {
                            put.wait();
                        }
                    }
                    TimeUnit.SECONDS.sleep(2);
                    bread++;
                    synchronized (get) {
                        get.notify();
                    }
                    System.out.println("put" + bread);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //消费者
        new Thread(() -> {
            for (; ; ) {
                try {
                    if (bread == 0) {
                        synchronized (get) {
                            get.wait();
                        }
                    }
                    TimeUnit.SECONDS.sleep(2);
                    bread--;
                    synchronized (put) {
                        put.notify();
                    }
                    System.out.println("get" + bread);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}