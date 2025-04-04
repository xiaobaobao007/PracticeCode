package per.bmy.Class;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

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

    @Test
    @SuppressWarnings("OctalInteger")
    public void BaseSystem() {
        System.out.println(0B10);
        System.out.println(017);
        System.out.println(0XF);
    }

    @Test
    public void test() {
        List<String> b = new ArrayList<>();
        b.add("1");
        b.add("12");
        b.add("123");
    }

    abstract class A {

    }

    class B extends A {

    }

    class C extends A {

    }

    @Test
    public void Instanceof() {
        A a = new B();
        A b = (B) a;
        System.out.println(b instanceof A);
        System.out.println(b instanceof B);
        System.out.println(b instanceof C);
    }

    enum BMY {
        Q(1),
        QW(12),
        QE(13);

        private int a;

        BMY(int a) {
            this.a = a;
        }
    }

    static int SIZE;

    static {
        SIZE = BMY.values().length;
    }

    @Test
    public void enumTest() {
        System.out.println(SIZE);
        for (BMY value : BMY.values()) {
            System.out.println(value.name() + ":" + value.a);
        }
    }

    @Test
    public void subListTest() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
//		System.out.println(list.size());
//		list.subList(1, 5).remove(0);
//		System.out.println(list.size());
        System.out.println(list.subList(0, 2).size());
        for (Integer integer : list.subList(0, 2)) {
            System.out.println(integer);
        }

    }
}
