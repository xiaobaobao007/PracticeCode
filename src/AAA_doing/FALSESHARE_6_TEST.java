package AAA_doing;

public class FALSESHARE_6_TEST implements Runnable {
	public static int NUM_THREADS = 4;
	public final static long ITERATIONS = 500L * 1000L * 1000L;
	private final int arrayIndex;
	private static VolatileLong[] longs;
	public static long SUM_TIME = 0l;

	public FALSESHARE_6_TEST(final int arrayIndex) {
		this.arrayIndex = arrayIndex;
	}

	public static void main(String[] s) throws Exception {
		Thread.sleep(10000);
		for (int j = 0; j < 10; j++) {
			System.out.println(j);
			longs = new VolatileLong[NUM_THREADS];
			for (int i = 0; i < longs.length; i++) {
				longs[i] = new VolatileLong();
			}
			final long start = System.nanoTime();
			runTest();
			final long end = System.nanoTime();
			SUM_TIME += end - start;
		}
		System.out.println("平均耗时：" + SUM_TIME / 10);
	}

	private static void runTest() throws InterruptedException {
		Thread[] threads = new Thread[NUM_THREADS];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new FALSESHARE_6_TEST(i));
		}
		for (Thread t : threads) {
			t.start();
		}
		for (Thread t : threads) {
			t.join();
		}
	}

	public void run() {
		long i = ITERATIONS + 1;
		while (0 != --i) {
			longs[arrayIndex].value = i;
		}
	}

	public final static class VolatileLong {
		public volatile long value = 0L;
		public long p1, p2, p3, p4, p5, p6;
	}
}


/**
 * 1：cpu读取内存之间有L1缓存，L2缓存，甚至L3缓存。
 * 2：缓存下一个单位是缓存行，缓存行通常是 64 字节。
 * 3：如果一个缓存行被多个线程更新维护，速度会变慢：比如两个被竞争的变量被存在一个缓存行，相互更新会竞争。
 * 4：Java 程序的对象头固定占 8 字节(32位系统)或 12 字节( 64 位系统默认开启压缩, 不开压缩为 16 字节)。
 * 5：64位下，剩余52字节，long=8字节，52/8=6余4。至少占用7个long，定义一个，再定义6个占位置。
 * 6：64位下，剩余52字节，int=4字节，52/4=13。至少占用13个int。
 * 结论：避免不同线程操作同一缓存行,措施，当类字节太小，进行无效字段填充
 */