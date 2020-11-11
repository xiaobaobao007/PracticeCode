import sun.misc.Unsafe;

class Solution implements Runnable {
	volatile int a = 0;

	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			a++;
		}
	}
}


public class Main {

	public static void main(String[] args) {
		Unsafe unsafe = Unsafe.getUnsafe();
		unsafe.allocateMemory(1024);
	}
}