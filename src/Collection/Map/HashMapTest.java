package Collection.Map;

import org.junit.Test;

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

	@Test
	public void add() {
		System.out.println(calcuteSeasonMvp(1, 2));
		System.out.println(calcuteSeasonMvp(1, 2));
		System.out.println(calcuteSeasonMvp(1, 2));
	}

	private Map<Integer, Integer> seasonMvpIdTimesMap;

	public boolean calcuteSeasonMvp(int newMvpId, int oldMvpId) {
		return calcuteMvp(seasonMvpIdTimesMap, newMvpId, oldMvpId);
	}

	public boolean calcuteMvp(Map<Integer, Integer> map, int newMvpId, int oldMvpId) {
		if (map == null) {
			map = new HashMap<>();
			map.put(newMvpId, 1);
			return true;
		}
		int newNum = map.compute(newMvpId, (k, v) -> v == null ? 1 : v + 1);
		if (oldMvpId == 0) {
			return true;
		}
		int oldNum = map.getOrDefault(oldMvpId, 0);
		if (newNum > oldNum) {
			return true;
		}
		return false;
	}

}
