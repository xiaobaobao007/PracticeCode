package Arithmetic;

import java.util.LinkedList;

/**
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 */
public class LongestValidParentheses {

	public static void main(String[] args) {
		System.out.println(new LongestValidParentheses().longestValidParentheses(")()())"));
	}

	public int longestValidParentheses(String s) {
		int max = 0;
		int num = 0;
		boolean go = false;
		LinkedList<Character> charList = new LinkedList<>();
		for (char c : s.toCharArray()) {
			if (!charList.isEmpty() && '(' == charList.getLast() && ')' == c) {
				if (max < ++num * 2) {
					max = num * 2;
					go = true;
				}
				charList.removeLast();
			} else {
				if (go) {
					go = false;
				} else {
					num = 0;
				}
				charList.addLast(c);
			}
		}
		return max;
	}

}