package Arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class GenerateParenthesis {

	public static void main(String[] args) {
		System.out.println(new GenerateParenthesis().generateParenthesis(3));
	}

	public List<String> generateParenthesis(int n) {
		List<String> list = new ArrayList<>();
		char[] chars = new char[n * 2];
		chars[0] = '(';
		qqq(list, chars, 1, 0, n);
		return list;
	}

	private void qqq(List<String> list, char[] sb, int a, int b, int n) {
		if (a == n && b == n) {
			list.add(String.copyValueOf(sb));
			return;
		}
		if (a <= b) {
			sb[a + b] = '(';
			qqq(list, sb, a + 1, b, n);
		} else {
			if (a < n) {
				sb[a + b] = '(';
				qqq(list, sb, a + 1, b, n);
			}
			if (b < n) {
				sb[a + b] = ')';
				qqq(list, sb, a, b + 1, n);
			}
		}
	}

}