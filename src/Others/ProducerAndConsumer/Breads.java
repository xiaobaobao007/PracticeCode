package Others.ProducerAndConsumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Breads {

    private Lock lock;
    private LinkedList<Integer> bread;

    public Breads() {
        lock = new ReentrantLock();
        bread = new LinkedList<>();
    }

    public Lock getLock() {
        return lock;
    }

    public LinkedList<Integer> getBread() {
        return bread;
    }

    public void setBread(LinkedList<Integer> bread) {
        this.bread = bread;
    }
}
