package ThreadProblem;

import java.util.concurrent.*;

/**
 * @author xiaobaobao
 * @date 2019/8/6 9:11
 */
public class FutureTaskTest {
    public static void main(String[] args) {
        get();
    }

    public static void get() {
        System.out.println("START");
        Callable<String> callable = () ->
        {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                    System.out.println(i);
                } catch (Exception e) {
                    System.out.println("error");
                    break;
                }
            }
            return "im over";
        };
        try {
            FutureTask<String> futureTask = new FutureTask<>(callable);
//            System.out.println(futureTask.cancel(false));
            new Thread(futureTask).start();
            futureTask.get(100, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}