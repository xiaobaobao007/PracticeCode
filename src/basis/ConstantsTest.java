package basis;

public class ConstantsTest {
	enum Constants2 { // 将常量放置在枚举类型中
		Constants_1A, Constants_1B
	}

	/**
	 * 定义一个方法，这里的参数为枚举类型对象
	 */
	public static void doit2(Constants2 c) {
		switch (c) { // 根据枚举类型对象做不同操作
			case Constants_1A:
				System.out.println("doit2() Constants_A");
				break;
			case Constants_1B:
				System.out.println("doit2() Constants_B");
				break;
		}
	}

	public static void main(String[] args) {
		ConstantsTest.doit2(Constants2.Constants_1A); // 使用枚举类型中的常量
		ConstantsTest.doit2(Constants2.Constants_1B); // 使用枚举类型中的常量
	}
}