package per.bmy.Class.interAndAbs;

/**
 * 类加载的顺序
 */
public class Test {
    public static void main(String[] ars) throws IllegalAccessException, InstantiationException, NoSuchMethodException, ClassNotFoundException {

        new ChildrenTest();//执行到此处,结果: 1a1b2a2b
        System.out.println();
//
        AbstractTest ab = new ChildrenTest();//执行到此处,结果: 2a2b
        System.out.println();

        Class name = Class.forName("per.bmy.Class.interAndAbs.ChildrenTest");//执行到此处,结果空，因为class已经被加载了
        System.out.println();

        ChildrenTest childrenTest = (ChildrenTest) name.newInstance();//执行到此处,结果: 2a2b

        int a = InterfaceTest.a;//接口定义的变量全局可访问

        test(childrenTest);//多态
        test1(childrenTest);//多态
    }

    private static void test(AbstractTest a) {
        a.abCon();
    }

    private static void test1(InterfaceTest a) {
        a.inTe();
    }


}