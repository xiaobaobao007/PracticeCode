package per.bmy.ThreadProblem;

import java.util.concurrent.Semaphore;

public class H2O {

    private final Semaphore H = new Semaphore(2);
    private final Semaphore O = new Semaphore(0);

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        H.acquire();
        releaseHydrogen.run();
        O.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        O.acquire(2);
        releaseOxygen.run();
        H.release(2);
    }

    public static void main(String[] args) {
        H2O h2O = new H2O();
        int n = 5;

        new Thread(() -> {
            for (int i = 0; i < 2 * n; i++) {
                try {
                    h2O.hydrogen(() -> System.out.print("H"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    h2O.oxygen(() -> System.out.print("O"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}