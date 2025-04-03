package per.bmy.Collection.List;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 线程安全的LRU缓存
 *
 * @param <K>
 * @param <V>
 * @author duminglei
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private static final long serialVersionUID = 1L;

    private int maxsize = 3;

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxsize;
    }

    public static void main(String[] args) {
        LRUCache<String, String> list = new LRUCache<>();
        for (int i = 0; i < 6; i++) {
            list.put("" + i, "" + i);
            System.out.println(list.toString());
        }
    }

}
