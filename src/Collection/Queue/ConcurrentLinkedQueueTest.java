package Collection.Queue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import Util.RandomUtil;

/**
 * @author bao meng yang
 * @date 2020/12/10，14:48
 */
public class ConcurrentLinkedQueueTest {

	ConcurrentLinkedQueue<String> waitQueue = new ConcurrentLinkedQueue<>();
	LinkedList<String> doingList = new LinkedList<>();

	ReentrantReadWriteLock readLock = new ReentrantReadWriteLock();

	Set<String> set = new HashSet<>();

	public static void main(String[] args) {
		new ConcurrentLinkedQueueTest().main();
	}

	public void main() {

		new Thread(() -> {
			addPeople("A");
		}).start();

		new Thread(() -> {
			addPeople("B");
		}).start();

		new Thread(() -> {
			addPeople("C");
		}).start();

		new Thread(() -> {
			try {
				for (; ; ) {
					Thread.sleep(200);
					waitToDoing();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	public void addPeople(String s) {
		int i = 0;
		int random = RandomUtil.nextInt(50) + 50;
		for (; ; ) {
			try {
				Thread.sleep(random);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			readLock.readLock().lock();
			waitQueue.add(s + (++i));
			readLock.readLock().unlock();
		}
	}

	public void waitToDoing() throws Exception {
		readLock.writeLock().lock();
		if (waitQueue.isEmpty()) {
			return;
		}
		doingList.addAll(waitQueue);
		waitQueue = new ConcurrentLinkedQueue<>();
		readLock.writeLock().unlock();
		match();
	}

	public void match() throws Exception {
		while (doingList.size() > 1) {
			if (!set.add(doingList.getFirst())) {
				throw new Exception();
			}
			if (!set.add(doingList.getLast())) {
				throw new Exception();
			}
			System.out.println(doingList.removeFirst() + "---" + doingList.removeLast());
		}
		System.out.println();
	}

}
