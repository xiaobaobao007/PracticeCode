package per.bmy.ThreadProblem.JUC.Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaobaobao
 * @date 2022/2/18，09:52
 * <p>
 * 允许初始化为 多个凭证，或者0个凭证
 * <p>
 * acquire 消耗凭证，必须有剩余的凭证存在，否则阻塞等待凭证的产生
 * <p>
 * release 释放凭证
 */
public class SemaphoreDown1 {

    private static final Semaphore semaphore = new Semaphore(0);

    static int i = 0;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i = 100;
            semaphore.release();
        }).start();

        semaphore.acquire();

        System.out.println(i);
    }
}