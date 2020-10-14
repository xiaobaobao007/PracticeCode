package Arithmetic.LeetCode_Hot;

import java.util.Collections;
import java.util.LinkedList;

/**
 * @author xiaobaobao
 * @date 2020/10/4，22:19
 */
public class DecodeString {

	public static void main(String[] args) {
		System.out.println(new DecodeString().decodeString("f2[e2[d2[c2[b2[a]]]]]"));
	}

	public String decodeString(String s) {
		char[] sarr = s.toCharArray();
		LinkedList<String> stack = new LinkedList<>();
		for (int i = 0; i < sarr.length; i++) {
			if ((sarr[i] > '0' && sarr[i] < '9')) {
				StringBuilder num = new StringBuilder(String.valueOf(sarr[i]));
				while (Character.isDigit(sarr[i + 1])) {
					i++;
					num.append(sarr[i]);
				}
				stack.push(num.toString());
			} else if (sarr[i] != ']') {
				stack.push(String.valueOf(sarr[i]));
			} else {
				LinkedList<String> sub = new LinkedList<>();
				while (!stack.peek().equals("[")) {
					sub.push(stack.pop());
				}
				stack.pop();
				String substr = getString(sub);
				int times = Integer.parseInt(String.valueOf(stack.pop()));
				StringBuilder build = new StringBuilder();
				for (int j = 0; j < times; j++) {
					build.append(substr);
				}
				stack.push(build.toString());
			}

		}
		Collections.reverse(stack);
		return getString(stack);
	}

	public String getString(LinkedList<String> sub) {
		StringBuilder substr = new StringBuilder();
		for (String c : sub) {
			substr.append(c);
		}
		return substr.toString();
	}

}
