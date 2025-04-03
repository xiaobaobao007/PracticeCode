package per.bmy.ThreadProblem;

import java.util.Random;

public class ThreadLocalRandomTest implements Runnable {

    private Random random;

    public static void main(String[] args) {
        Random random = new Random();
        ThreadLocalRandomTest run = new ThreadLocalRandomTest(random);
//		new Thread()
    }

    public ThreadLocalRandomTest(Random random) {
        this.random = random;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            random.nextInt(6);
        }
//		System.out.println(System.get);
    }
}



