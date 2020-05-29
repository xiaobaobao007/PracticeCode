package Collection.Map;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

	public static void main(String[] argv) {
		Map<Integer, Integer> map = new HashMap<>();
		//对key和value存在才计算
		map.put(0, 100);
		map.computeIfPresent(0, (k, v) -> k + 10);
		System.out.println(map.get(0));
		map.put(0, 100);
		map.computeIfPresent(0, (k, v) -> v + 10);
		System.out.println(map.get(0));
	}

}
