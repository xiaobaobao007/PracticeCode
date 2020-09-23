/**
 * @author xiaobaobao
 * @date 2020/4/28，19:48
 */
public class Main {
	public static void main(String[] args) {
		System.out.println(stringContainsStringBySplit("123_456_789", "456", '_'));
		System.out.println(stringContainsStringBySplit("456_789_123", "456", '_'));
		System.out.println(stringContainsStringBySplit("789_123_456", "456", '_'));
		System.out.println(stringContainsStringBySplit("456", "456", '_'));
		System.out.println(stringContainsStringBySplit("456_", "456", '_'));
		System.out.println(stringContainsStringBySplit("_456", "456", '_'));
		System.out.println();
		System.out.println(stringContainsStringBySplit("45", "456", '_'));
		System.out.println(stringContainsStringBySplit("56", "456", '_'));
		System.out.println(stringContainsStringBySplit("1456", "456", '_'));
		System.out.println(stringContainsStringBySplit("4561", "456", '_'));
		System.out.println(stringContainsStringBySplit("123_4561_789", "456", '_'));
		System.out.println(stringContainsStringBySplit("123_1456_789", "456", '_'));
		System.out.println(stringContainsStringBySplit("4561_789_123", "456", '_'));
		System.out.println(stringContainsStringBySplit("1456_789_123", "456", '_'));
		System.out.println(stringContainsStringBySplit("789_123_1456", "456", '_'));
		System.out.println(stringContainsStringBySplit("789_123_4561", "456", '_'));
	}

	/**
	 * 字符串是否包含另一个字符串，并被分隔符分割
	 * 用于优化字符串检测时，先转集合，再判断
	 *
	 * @param src   被判断的字串
	 * @param s     是否包含的字符串
	 * @param split 分隔符
	 * @return 是否包含的有
	 */
	public static boolean stringContainsStringBySplit(String src, String s, char split) {
		int index = src.indexOf(s);
		if (index < 0) {
			return false;
		}
		if (src.length() == s.length()) {
			return true;
		}
		if (index == 0) {
			return split == src.charAt(index + s.length());
		}
		if (index + s.length() == src.length()) {
			return split == src.charAt(index - 1);
		}
		return split == src.charAt(index + s.length()) && split == src.charAt(index - 1);
	}
}
