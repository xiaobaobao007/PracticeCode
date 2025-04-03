package per.bmy.ThreadProblem;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaobaobao
 * @date 2020/4/10，14:13
 */
public class ExchangerTest {
    static class Producer extends Thread {

        private Exchanger<String> exchanger;
        private static String data;

        Producer(String name, Exchanger<String> exchanger) {
            super("Producer-" + name);
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            for (int i = 1; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    data = exchanger.exchange("+" + data + "+");
                    System.out.println(getName() + " 交换后:" + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer extends Thread {
        private Exchanger<String> exchanger;
        private static String data;

        Consumer(String name, Exchanger<String> exchanger) {
            super("Consumer-" + name);
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (true) {
//				System.out.println(getName() + " 交换前:" + data);
                try {
                    TimeUnit.MILLISECONDS.sleep(3000);
                    data = exchanger.exchange("-" + data + "-");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getName() + " 交换后:" + data);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        new Producer("", exchanger).start();
        new Consumer("", exchanger).start();
        TimeUnit.SECONDS.sleep(7);
        System.exit(-1);
    }
}