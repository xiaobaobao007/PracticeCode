package Reflect;

import ClassProblem.ChildrenTest;

/**
 * @author xiaobaobao
 * @date 2019/6/29 17:32
 */
public class AllMethods {
	public static void main(String[] args) {
		ChildrenTest test = new ChildrenTest();
		Class c = test.getClass();
		System.out.println(c.getPackage());
		System.out.println(c.getName());
		System.out.println(c.getSuperclass());
		System.out.println(c.getInterfaces());
		System.out.println(c.getConstructors());
		System.out.println(c.getDeclaredConstructors());
		System.out.println(c.getFields());
		System.out.println(c.getDeclaredFields());
		System.out.println(c.getClasses());
		System.out.println(c.getDeclaredClasses());
		System.out.println(c.getDeclaringClass());
	}
}
