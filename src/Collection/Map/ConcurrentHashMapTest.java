package Collection.Map;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentHashMapTest implements Runnable {

	private Map<Integer, Integer> map;

	public ConcurrentHashMapTest(Map<Integer, Integer> map) {
		this.map = map;
	}

	public static void main(String[] argv) {
		Map<Integer, Integer> map = new ConcurrentHashMap<>();
		Runnable run = new ConcurrentHashMapTest(map);
		ExecutorService threadPool = Executors.newCachedThreadPool();
		threadPool.submit(run);
		threadPool.submit(run);
		threadPool.submit(run);
	}

	@Override
	public void run() {
		Random random = new Random();
		try {
			while (true) {
				Thread.sleep(1000);
				int i = random.nextInt(10);
				Integer value = map.getOrDefault(i, 0);
				map.put(i, value + 1);
				System.out.println(Thread.currentThread().getId() + ":" + map.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
