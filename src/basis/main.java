package basis;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author xiaobaobao
 * @date 2020/3/29，20:08
 * <p>
 * 私有的单例构造器并不安全
 */
public class main {

	static class Singleton {
		private volatile static Singleton uniqueInstance;

		private Singleton() {
		}

		private Singleton(Integer a) {
			System.out.println(a);
		}

		public static Singleton getInstance() {
			if (uniqueInstance == null) {
				synchronized (Singleton.class) {
					if (uniqueInstance == null) {//进入区域后，再检查一次，如果仍是null,才创建实例
						uniqueInstance = new Singleton();
					}
				}
			}
			return uniqueInstance;
		}
	}

	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Singleton s = Singleton.getInstance();
		Singleton sUsual = Singleton.getInstance();
		Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor(Integer.class);
		//取消 Java 语言访问检查
		constructor.setAccessible(true);
		Singleton sReflection = constructor.newInstance(123);
		System.out.println(s + "\n" + sUsual + "\n" + sReflection);
		System.out.println("正常情况下，实例化两个实例是否相同：" + (s == sUsual));
		System.out.println("通过反射攻击单例模式情况下，实例化两个实例是否相同：" + (s == sReflection));
	}
}
