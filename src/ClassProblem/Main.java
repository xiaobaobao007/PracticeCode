package ClassProblem;

public class Main {

	public static void main(String[] args) {
		Father fs = new Son();
		System.out.println(fs.getClass());
		fs.sayHi();
		fs.ff();
		((Son) fs).ss();

		System.out.println("---------------------------");

		Son sf = (Son) new Father();
		System.out.println(sf.getClass());
		sf.sayHi();
		sf.ff();
		sf.ss();
	}

}

class Father {

	Father() {
	}

	void ff() {
		System.out.println("Hi,aa!I'm ClassProblem.Father.");
	}

	public void sayHi() {
		System.out.println("Hi,World!I'm ClassProblem.Father.");
	}

}

class Son extends Father {

	void ss() {
		System.out.println("Hello,World!I'm ClassProblem.Son.");
	}

	@Override
	public void sayHi() {
		System.out.println("Hi,World!I'm ClassProblem.Son.");
	}
}