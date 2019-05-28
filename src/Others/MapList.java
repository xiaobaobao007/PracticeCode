package Others;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapList {
    public static void main(String[] args) {
        Map<Integer, String> maps = new HashMap<>();
        maps.put(2, "222");
        maps.put(1, "111");
        maps.put(3, "333");
        for (Integer i : maps.keySet()) {
            System.out.println(maps.get(i));
        }

        for (Map.Entry<Integer, String> m : maps.entrySet()) {
            System.out.printf("key:%d,value:%s\n", m.getKey(), m.getValue());
        }

        Iterator<Map.Entry<Integer, String>> it = maps.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> in = it.next();
            System.out.printf("key:%d,value:%s\n", in.getKey(), in.getValue());
        }
    }
}
