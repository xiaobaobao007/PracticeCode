package per.bmy.Class;

/**
 * @author xiaobaobao
 * @date 2020/6/4，17:32
 */
public class VolatileTest {

    int i;

    volatile int a;

    public void VolatileTest() {

        i = 0;

        i = a;

        a = i;

    }

    public static void main(String[] args) {

    }

}
