package per.bmy.ThreadProblem;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaobaobao
 * @date 2020/3/30，21:38
 */
public class Demo2 {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
        Future<?> submit = pool.submit(Demo2::bmy);
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(submit.isDone());
        }
    }

    private static void bmy() {
        int i = 10;
        while (i-- > 0) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}