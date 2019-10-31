package Collection.Map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xiaobaobao
 * @date 2019/10/19，12:05
 */
public class LinkedHashMapTest<K, V> extends LinkedHashMap<K, V> {
    LinkedHashMapTest(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    public static void main(String[] args) {
        Map<Integer, Integer> map = new LinkedHashMapTest<>(10, 0.75F, true);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.forEach((k, v) -> System.out.println(k + "," + v));
    }

//    @Override
//    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
//        return size() > 2;
//    }
}
