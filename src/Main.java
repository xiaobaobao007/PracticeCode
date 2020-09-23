/**
 * @author xiaobaobao
 * @date 2020/4/28，19:48
 */
public class Main {
	public static void main(String[] args) {
		System.out.println(get("123_456_789", "456", '_'));
		System.out.println(get("456_789_123", "456", '_'));
		System.out.println(get("789_123_456", "456", '_'));
		System.out.println(get("456", "456", '_'));
		System.out.println(get("456_", "456", '_'));
		System.out.println(get("_456", "456", '_'));
		System.out.println();
		System.out.println(get("45", "456", '_'));
		System.out.println(get("56", "456", '_'));
		System.out.println(get("1456", "456", '_'));
		System.out.println(get("4561", "456", '_'));
		System.out.println(get("123_4561_789", "456", '_'));
		System.out.println(get("123_1456_789", "456", '_'));
		System.out.println(get("4561_789_123", "456", '_'));
		System.out.println(get("1456_789_123", "456", '_'));
		System.out.println(get("789_123_1456", "456", '_'));
		System.out.println(get("789_123_4561", "456", '_'));
	}

	public static boolean get(String a, String b, char c) {
		int index = a.indexOf(b);
		if (index < 0) {
			return false;
		}
		if (a.length() == b.length()) {
			return true;
		}
		if (index == 0) {
			return c == a.charAt(index + b.length());
		}
		if (index + b.length() == a.length()) {
			return c == a.charAt(index - 1);
		}
		return c == a.charAt(index + b.length()) && c == a.charAt(index - 1);
	}
}
