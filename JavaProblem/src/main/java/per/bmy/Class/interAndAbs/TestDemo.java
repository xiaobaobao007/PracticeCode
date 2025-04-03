package per.bmy.Class.interAndAbs;

import org.junit.Test;

/**
 * @author xiaobaobao
 * @date 2020/7/8，10:05
 */
public final class TestDemo {
    /*
    public: 1
    private: 2
    protected: 4
    static: 8
    final: 16
    synchronized: 32
    volatile: 64
    transient: 128
    native: 256
    interface: 512
    abstract: 1024
    strict: 2048
    */
    @Test
    public void getModifiers() {
        System.out.println(AbstractTest.class.getModifiers());
        System.out.println(TestDemo.class.getModifiers());
    }
}
