package per.bmy.Class;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author xiaobaobao
 * @date 2020/3/29，19:40
 */
public class Singleton {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) throws Exception {
        Singleton singleton = new Singleton();
        //调用方法 设置参数
        Method set = singleton.getClass().getMethod("setAge", int.class);
        set.invoke(singleton, 321);
        System.out.println(singleton.getAge());
        //调用方法 获取参数
        Method get = singleton.getClass().getMethod("getAge");
        Object invoke = get.invoke(singleton);
        System.out.println(invoke);
        //
        Field field = singleton.getClass().getDeclaredField("age");
        System.out.println(field.get(singleton));
    }
}