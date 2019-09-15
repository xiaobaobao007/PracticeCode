package Java8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaobaobao
 * @date 2019/9/3，15:49
 */
public class Stream {
    public static void main(String[] args) {
        test1(Stream::test2);
//        test1(System.out::println);
    }

    public static void test1(Runnable runnable) {
        runnable.run();
        System.out.println(2);
    }

    public static void test2() {
        System.out.println(1);
    }
}
