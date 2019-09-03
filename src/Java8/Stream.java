package Java8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaobaobao
 * @date 2019/9/3，15:49
 */
public class Stream {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i+",");
        }
        list.parallelStream().forEach(System.out::print);
    }
}
