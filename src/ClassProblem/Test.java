package ClassProblem;

import java.util.HashMap;

/**
 * 类加载的顺序
 */
public class Test {
	public static void main(String[] ars) throws IllegalAccessException, InstantiationException, NoSuchMethodException, ClassNotFoundException {

//		new ChildrenTest();//执行到此处,结果: 1a1b2a2b
//		System.out.println();
//		AbstractTest ab = new ChildrenTest();//执行到此处,结果: 2a2b
//		Class name = Class.forName("ChildrenTest");
//		ChildrenTest childrenTest = (ChildrenTest) name.newInstance();
//		Constructor<ChildrenTest> declaredConstructor = ChildrenTest.class.getDeclaredConstructor();
//
//		int a = InterfaceTest.a;

		HashMap<Object, Object> map = new HashMap<>();
		for (int i = 16; i < 600; i += 16) {
			map.put(i, i);
		}

	}
}