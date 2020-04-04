package LoadBalancing;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

	public static Set<String> set = new TreeSet<>();

	static {
		set.add("1");
		set.add("2");
		set.add("3");
		set.add("4");
	}

	public static void main(String[] args) {
	}

	public void RoundRobin() {
		AtomicInteger index = new AtomicInteger(0);
	}

}