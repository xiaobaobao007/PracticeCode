package StringOperator;

/**
 * @author xiaobaobao
 * @date 2019/6/24 16:07
 * 给定一个字符串，请你找出其中最长不含有重复字符的 最长子串 的长度。
 */
public class Main2 {

	Main2() {
		String s = "15189871841313794168641351";
		int size = 0;
		int max = 0;
		int[] result = new int[300];
		int length = s.length();
		for (int i = 0; i < length; i++) {
			if (i == 1 && s.charAt(0) == s.charAt(1)) {
				size = 1;
				max = 1;
			}
			char c = s.charAt(i);
			if (result[c] == 0) {
				result[c] = i;
				if (++size > max) {
					max = size;
					System.out.println(i + ":" + max + ":" + c);
				}
			} else {
				int b = i - size;
				size = i - result[c];
				for (int j = result[c] - 1; j >= b; j--) {
					result[s.charAt(j)] = 0;
				}
			}
		}
		System.out.println(max);
	}

	public static void main(String[] args) {
		new Main2();
	}
}
