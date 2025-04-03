package per.bmy.Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Student {
    private String name = "小花";
    public int age = 4;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void say(String man) {
        System.out.println(man + "大声说话。。。");
    }

    private void listen(String man) {
        System.out.println(man + "小声听歌");
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        //获取Student的Class对象，后面的所有操作都是根据这个来的
        Class<Student> clazz = Student.class;

        //获取全类名
        String className = clazz.getName();
        System.out.println("1:" + className);

        //获取类中所有属性全名
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("2:" + fields[i]);
        }

        //获取类中所有方法全名
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println("3:" + methods[i]);
        }

        //根据空构造器来实例化对象，并调用其中的listen方法
        Student instance = clazz.newInstance();
        System.out.print("4:");
        instance.listen("小王");

        //首先通过空构造器实例对象，然后获取指定方法名（这里会指定方法参数类型），然后通过invoke方法来调用该指定方法
        //注意这种调用方法和上面这种的区别，好像是这种可以捕捉异常，更安全吧！
        Student instance3 = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("say", String.class);
        System.out.print("5:");
        method.invoke(instance3, "张三");

        //调用空构造器实例对象，获取指定属性名，注意，假如该属性是私有的，一定要调用field.setAccessible(true)，不然会报错
        //然后就是设置属性值
        Student instance4 = clazz.newInstance();
        Field field = clazz.getDeclaredField("age");
        field.setAccessible(true);
        field.set(instance4, 20);
        System.out.println("6:" + instance4.getAge());

        //调用有参构造器传入参数来实例化对象，并调用其中的toString（）方法
        Constructor<Student> constructor = clazz.getDeclaredConstructor(String.class, int.class);
        Student instance2 = constructor.newInstance("java小新人", 18);
        System.out.println("7:" + instance2.toString());

    }
}