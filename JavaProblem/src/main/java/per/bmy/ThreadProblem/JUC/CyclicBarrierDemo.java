package per.bmy.ThreadProblem.JUC;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xiaobaobao
 * @date 2020/5/11，23:34
 * <p>
 * 相比较与CountDownLatch,更能重复利用
 */
public class CyclicBarrierDemo {

    static class TaskThread extends Thread {

        CyclicBarrier barrier;

        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(getName() + " 到达栅栏 A");
                barrier.await();

                Thread.sleep(3000);
                System.out.println(getName() + " 到达栅栏 B");
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1);
        int await = -1000;
        try {
            System.out.println("start");
            // await = cyclicBarrier.await(2, TimeUnit.SECONDS);
            await = cyclicBarrier.await();
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
            // } catch (TimeoutException e) {
            // 	e.printStackTrace();
        } finally {
            System.out.println(await);
        }

    }

}
