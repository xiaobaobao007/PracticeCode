package per.bmy.ThreadProblem;

/**
 * @author xiaobaobao
 * @date 2020/3/29，23:10
 * <p>
 * 简单的死锁例子
 */
class SynThread implements Runnable {

    int a, b;

    public SynThread(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (Integer.valueOf(a)) {//必须用valueOf()方法
            synchronized (Integer.valueOf(b)) {
                System.err.println("a+b==" + (a + b));
            }
        }
    }

}

public class entry {

    public static void main(String[] args) {

        //循环主要是为了加大死锁概率
        for (int i = 0; i < 100; i++) {
            new Thread(new SynThread(1, 2)).start();
            new Thread(new SynThread(2, 1)).start();
        }
    }
}