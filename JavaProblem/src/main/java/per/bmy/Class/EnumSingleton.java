package per.bmy.Class;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author xiaobaobao
 * @date 2020/3/29，20:53
 */
public enum EnumSingleton {
    INSTANCE;

    public EnumSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        EnumSingleton singleton1 = EnumSingleton.INSTANCE;
        EnumSingleton singleton2 = EnumSingleton.INSTANCE;
        System.out.println("正常情况下，实例化两个实例是否相同：" + (singleton1 == singleton2));
        Constructor<EnumSingleton> constructor;
        constructor = EnumSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        EnumSingleton singleton3 = null;
        singleton3 = constructor.newInstance();
        System.out.println(singleton1 + "\n" + singleton2 + "\n" + singleton3);
        System.out.println("通过反射攻击单例模式情况下，实例化两个实例是否相同：" + (singleton1 == singleton3));
    }
}