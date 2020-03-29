package JVM;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaobaobao
 * @date 2020/3/27，18:34
 */
public class Finalizable {

	static AtomicInteger aliveCount = new AtomicInteger(0);

	Finalizable() {
		aliveCount.incrementAndGet();
	}

	@Override
	protected void finalize() throws Throwable {
		Finalizable.aliveCount.decrementAndGet();
	}

	public static void main(String args[]) {
		for (int i = 0; ; i++) {
			Finalizable f = new Finalizable();
			if ((i % 100_000) == 0) {
				System.out.format("After creating %d objects, %d are still alive.%n", i, Finalizable.aliveCount.get());
			}
		}
	}
}