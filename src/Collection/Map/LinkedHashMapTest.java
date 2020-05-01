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
		//第三个参数为是否为访问顺序,true访问顺序，false插入顺序，默认fasle
		Map<Integer, Integer> map = new LinkedHashMapTest<>(10, 0.75F, true);
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		map.put(4, 4);
		System.out.println(map.toString());
		map.get(2);
		System.out.println(map.toString());
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() > 2;
	}
}
