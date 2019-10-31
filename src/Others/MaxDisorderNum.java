package Others;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class MaxDisorderNum {


	public static void main(String[] args) {
		System.out.println(new MaxDisorderNum().lengthOfLongestSubstring("abcabcbb"));
	}

	public int lengthOfLongestSubstring(String s) {
		int max = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int start = 0, end = 0; end < s.length(); end++) {
			char charAt = s.charAt(end);
			if (map.containsKey(charAt)) {
				start = Math.max(map.get(charAt), start);
			}
			max = Math.max(max, end - start + 1);
			map.put(charAt, end + 1);
		}
		return max;
	}
}
