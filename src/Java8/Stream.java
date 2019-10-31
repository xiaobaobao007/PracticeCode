package Java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaobaobao
 * @date 2019/9/3，15:49
 */
public class Stream {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("123", "12", "12345", "1", "1234");
        List<String> collect = strings.stream().filter(s -> s.length() >= 3).collect(Collectors.toList());
        collect.forEach(System.out::println);

//        test1(Stream::test2);
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
