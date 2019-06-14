package Collection.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListTest {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
//        List<Integer> list = Collections.synchronizedList(new ArrayList<>());//synchronized
//        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();//并发容器
        Collection<String> stringCollection = new ArrayList<>();
        stringCollection.add("1");
        stringCollection.add("2");
        stringCollection.add("3");
        stringCollection.add("4");

        List<String> stringList = new ArrayList<>(stringCollection);

        int length=5;
        int[] array=new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }

        int[] ints = Arrays.copyOf(array, 10);
        for (int s : ints) {
            System.out.println(s);
        }
    }
}
