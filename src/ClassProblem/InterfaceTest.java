package ClassProblem;

/**
 * 接口中定义的变量默认是public static final 型
 */
interface InterfaceTest {

	int a = 1234;
	Integer aa = null;

	static void a() {
		System.out.println(123);
	}

	default void inTe() {
		System.out.println("===============1");
	}

	default void inTT() {
		System.out.println("================2");
	}

	void inTr();


}
