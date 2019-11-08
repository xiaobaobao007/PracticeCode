package StringOperator;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author xiaobaobao
 * @date 2019/6/24 14:58
 * <p>
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 * <p>
 * 示例：
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 */
public class Main1 {
	int min = Integer.MAX_VALUE;
	int max = Integer.MIN_VALUE;

	Main1() {
		String T = "ABC";
		String S = "ADOBECODEBANC";
		Map<Character, Integer> map = new HashMap<>();
		int index = 0;
		for (int i = 0; i < T.length(); i++) {
//            int count = map.getOrDefault(T.charAt(i), 0);
			if (map.get(T.charAt(i)) == null) {
				map.put(T.charAt(i), index++);
			}
		}
		//位置和值
		Map<Integer, Integer> S_map = new HashMap<>();
		for (int i = 0; i < S.length(); i++) {
			if (map.get(S.charAt(i)) != null) {
				S_map.put(i, map.get(S.charAt(i)));
			}
		}
		Map<Integer, Integer> result = new HashMap<>();
		for (Map.Entry<Integer, Integer> entry : S_map.entrySet()) {
			test1(entry.getKey(), entry.getValue(), result);
		}
		System.out.println(S.substring(min, max + 1));
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			String uuid = UUID.randomUUID().toString();
			System.out.println(uuid);
		}
//        new Main1();
	}


	public void reset() {
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
	}

	public void test1(int index, int value, Map<Integer, Integer> result) {
		if (result.get(value) != null) {
			if (result.get(value) == min || result.get(value) == max) {
				reset();
				result.put(value, index);
				for (Integer i : result.values()) {
					if (i < min) {
						min = i;
					}
					if (i > max) {
						max = i;
					}
				}
			} else {
				result.put(value, index);
			}
		} else {
			result.put(value, index);
			if (index < min) {
				min = index;
			}
			if (index > max) {
				max = index;
			}
		}
	}
}