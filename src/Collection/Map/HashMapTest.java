package Collection.Map;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    public static void main(String[] argv) {
        Map<Integer, Integer> string = new HashMap<>();
        put(string, null);
        for (int i = 0, j = 0; j < 10; i += 100, j++) {
            put(string, i);
        }
        sout(string);

        System.out.println(1^2);
    }

    private static void put(Map<Integer, Integer> map, Integer value) {
        map.put(value, value);
    }

    private static void sout(Map<Integer, Integer> map) {
        for (Object o : map.keySet()) {
            System.out.println(o + "," + map.get(o));
        }
    }
}