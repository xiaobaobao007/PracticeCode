package Reflect;

import ClassProblem.ChildrenTest;

import java.util.Arrays;

/**
 * @author xiaobaobao
 * @date 2019/6/29 17:32
 */
public class AllMethods {
	public static void main(String[] args) {
		ChildrenTest test = new ChildrenTest();
		Class c = test.getClass();
		System.out.println("package:" + c.getPackage());
		System.out.println("name:" + c.getName());
		System.out.println("superclass:" + c.getSuperclass());
		System.out.println("interfaces:" + Arrays.toString(c.getInterfaces()));
		System.out.println("constructors:" + Arrays.toString(c.getConstructors()));
		System.out.println("declaredConstructors:" + Arrays.toString(c.getDeclaredConstructors()));
		System.out.println("fields:" + Arrays.toString(c.getFields()));
		System.out.println("declaredFields:" + Arrays.toString(c.getDeclaredFields()));
		System.out.println("classes:" + Arrays.toString(c.getClasses()));
		System.out.println("declaredClasses:" + Arrays.toString(c.getDeclaredClasses()));
		System.out.println("declaringClass:" + c.getDeclaringClass());
	}
}
