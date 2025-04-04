package per.bmy.Jvm;

/**
 * GCRoots 测试：虚拟机栈（栈帧中的局部变量）中引用的对象作为GCRoots
 * -Xms1024m -Xmx1024m -Xmn256m -XX:+PrintGCDetails
 * <p>
 * 扩展：虚拟机栈中存放了编译器可知的八种基本数据类型,对象引用,returnAddress类型（指向了一条字节码指令的地址）
 */
public class TestGCRoots01 {
    private int _80000K = 10 * 1024 * 1024;
    private byte[] memory = new byte[_80000K];

    public static void main(String[] args) {
        method01();
        System.out.println("返回main方法");
        System.gc();
        System.out.println("第二次GC完成");
    }

    public static void method01() {
        TestGCRoots01 t = new TestGCRoots01();
        System.gc();
        System.gc();
        System.out.println("第一次GC完成");
    }
}