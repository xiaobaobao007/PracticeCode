package per.bmy.Class.interAndAbs;


/**
 * 1. 抽象类不能被实例化。
 * 2. 有抽象方法的类必定是抽象类。
 * 3. 抽象类中的抽象方法只能声明。
 * 4. 构造方法，静态方法不能声明为抽象方法。
 * 5. 抽象类的非抽象子类必须给出抽象类中的抽象方法的具体实现。
 */
abstract class AbstractTest {
    static {
        System.out.print("1a");
    }

    AbstractTest() {
        System.out.print("2a");
    }

    public static void init() {
    }

    abstract public void abCon();

}
