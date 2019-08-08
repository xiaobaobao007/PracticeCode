package Collection.Queue;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author xiaobaobao
 * @date 2019/8/8 11:20
 */
public class SynchronousQueueTest {

    static class SynchronousQueueProducer implements Runnable {

        protected BlockingQueue<String> blockingQueue;
        final Random random = new Random();

        public SynchronousQueueProducer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String data = UUID.randomUUID().toString();
                    System.out.println("Put: " + data);
                    blockingQueue.put(data);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    static class SynchronousQueueConsumer implements Runnable {

        protected BlockingQueue<String> blockingQueue;

        public SynchronousQueueConsumer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    long start = System.currentTimeMillis();
                    String data = blockingQueue.take();
                    System.out.println(System.currentTimeMillis() - start + "//" + Thread.currentThread().getName() + " take(): " + data);
//                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " 没有拿到");
                }
            }
        }

    }

    public static void main(String[] args) {
        final BlockingQueue<String> synchronousQueue = new SynchronousQueue<String>(true);//公平锁

        SynchronousQueueProducer queueProducer1 = new SynchronousQueueProducer(synchronousQueue);
        new Thread(queueProducer1).start();

        SynchronousQueueConsumer queueConsumer1 = new SynchronousQueueConsumer(synchronousQueue);
        new Thread(queueConsumer1).start();

        SynchronousQueueConsumer queueConsumer2 = new SynchronousQueueConsumer(synchronousQueue);
        new Thread(queueConsumer2).start();

    }
}