package per.bmy.Jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import org.junit.Test;

/**
 * @author xiaobaobao
 * @date 2019/9/3，15:49
 */
public class Stream {

    /**
     * stream只能使用一次
     */
    @Test
    public void demo1() {
        List<String> list = new ArrayList<>(Arrays.asList("one", "two"));
        java.util.stream.Stream<String> stream = list.stream();
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);
    }

    /**
     * stream 复用的正确方法
     */
    @Test
    public void demo2() {
        Supplier<List<String>> supplier = () -> new ArrayList<>(Arrays.asList("one", "two"));
        java.util.stream.Stream<String> stream1 = supplier.get().stream();
        stream1.forEach(System.out::println);
        java.util.stream.Stream<String> stream2 = supplier.get().stream();
        stream2.forEach(System.out::println);
    }

    @Test
    public void demo4() {
        List<String> l = new ArrayList(Arrays.asList("1", "2", "3", "4"));
        class State {
            boolean s;
        }
        final State state = new State();
        java.util.stream.Stream<String> sl = l.stream().map(e -> {
            if (state.s) return "OK";
            else {
                state.s = true;
                return e;
            }
        });
        sl.forEach(System.out::println);
    }

}
