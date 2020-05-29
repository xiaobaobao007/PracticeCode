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

		//对value存不存在分别计算
		map.compute(1, (k, v) -> {
			if (v == null) {
				return 10;
			} else {
				return v + 1;
			}
		});
		System.out.println(map.get(1));

		map.compute(1, (k, v) -> {
			if (v == null) {
				return 10;
			} else {
				return v + 1;
			}
		});
		System.out.println(map.get(1));
	}

}
