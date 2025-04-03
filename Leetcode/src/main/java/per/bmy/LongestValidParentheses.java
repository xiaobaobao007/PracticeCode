package per.bmy;

/**
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        System.out.println(new LongestValidParentheses().longestValidParentheses("()(()"));
    }

    public int longestValidParentheses(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    // public int longestValidParentheses(String s) {
    // 	int n = 0;
    // 	int m = 0;
    // 	for (char c : s.toCharArray()) {
    // 		if ('(' == c) {
    // 			n++;
    // 		} else {
    // 			n--;
    // 			if (n < 0) {
    // 				n = 0;
    // 			} else {
    // 				m++;
    // 			}
    // 		}
    // 	}
    // 	return m << 1;
    // }
}