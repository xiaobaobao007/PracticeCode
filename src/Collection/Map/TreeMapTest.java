package Collection.Map;

import java.util.TreeMap;

/**
 * @author xiaobaobao
 * @date 2019/7/8 11:42
 */
public class TreeMapTest {
	public static void main(String[] args) {
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		treeMap.put(1, 1);
		treeMap.put(3, 1);
		treeMap.put(2, 1);
		System.out.println(treeMap.keySet());
	}
}
