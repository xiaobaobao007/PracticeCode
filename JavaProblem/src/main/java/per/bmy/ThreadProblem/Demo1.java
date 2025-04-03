package per.bmy.ThreadProblem;

import java.util.OptionalInt;

/**
 * @author xiaobaobao
 * @date 2020/3/29，23:21
 */
class MyThread1 implements Runnable {

    public Object object;

    public MyThread1(Object object) {
        this.object = object;
    }

    public void waitTest() {
        synchronized (object) {
            System.out.println("MyThread1 start");
            try {
                object.wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThread1 end");
        }

    }

    public void run() {
        this.waitTest();
    }
}

class MyThread2 implements Runnable {

    public Object object;
    public Thread thread;

    public MyThread2(Object object) {
        this.object = object;
    }

    public MyThread2(Object object, Thread thread) {
        this.object = object;
        this.thread = thread;
    }

    public void waitTest() {
        synchronized (object) {
            System.out.println("MyThread2 start");
            try {
//                object.wait(6000);
                System.out.println("1 join 2");
                thread.join();
                System.out.println("1 join 2 结束");
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThread2 end");
        }
    }

    public void run() {
        this.waitTest();
    }
}

public class Demo1 {
    volatile static int a = 1;

    public static void main(String[] args) {
//		new Demo1();
//		new Demo1();
        StringBuilder sb = new StringBuilder();
        sb.append("123456");
        System.out.println(sb);
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);

        OptionalInt.of(a);
    }

    public Demo1() {
//		new test();
    }

    static class test {
        static {
            System.out.println("1111");
        }

        public test() {
            System.out.println("2222");
        }
    }
}

