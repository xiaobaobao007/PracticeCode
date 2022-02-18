package ThreadProblem.JUC;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaobaobao
 * @date 2019/7/6 15:20
 * <p>
 * 一次性，相应数量down后才唤醒await
 */
public class CountDownLatchDemo {

    static final CountDownLatch latch = new CountDownLatch(1);

    static int i = 0;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i = 100;
            latch.countDown();
        }).start();

        latch.await();

        System.out.println(i);
    }
}