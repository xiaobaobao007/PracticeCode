package ThreadProblem;

public class thread implements Runnable {

	ThreadLocal<String> num = new ThreadLocal<>();

	public thread(String num) {
		this.num.set(num);
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			num.set(num.get() + 1);
			System.out.println(num.get());
		}
	}
}
