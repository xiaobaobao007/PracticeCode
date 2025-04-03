package per.bmy.LeetCode_Hard;

import java.util.Arrays;

/**
 * <pre>
 *                   115. 不同的子序列
 *                   尝试过
 *                   困难
 *                   相关标签
 *                   相关企业
 *                   给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数，结果需要对 109 + 7 取模。
 *
 *
 *
 *                   示例 1：
 *
 *                   输入：s = "rabbbit", t = "rabbit"
 *                   输出：3
 *                   解释：
 *                   如下所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 *                   rabbbit
 *                   rabbbit
 *                   rabbbit
 *                   示例 2：
 *
 *                   输入：s = "babgbag", t = "bag"
 *                   输出：5
 *                   解释：
 *                   如下所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 *                   babgbag
 *                   babgbag
 *                   babgbag
 *                   babgbag
 *                   babgbag
 *
 *
 *                   提示：
 *
 *                   1 <= s.length, t.length <= 1000
 *                   s 和 t 由英文字母组成
 * </pre>
 *
 * @author baomengyang
 * @date 2025/4/3 13:54:20
 */
public class _115_NumDistinct {
    public static void main(String[] args) {
        test("rabbbit", "rabbit");
        test("babgbag", "bag");
        test("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    static void test(String s, String t) {
        long time1 = System.currentTimeMillis();
        int result1 = new Solution().numDistinct(s, t);
        time1 = System.currentTimeMillis() - time1;

        long time2 = System.currentTimeMillis();
        int result2 = new Solution_1().numDistinct(s, t);
        time2 = System.currentTimeMillis() - time2;

        System.out.printf("res1:%-5d   time1:%5d  res2:%-5d   time2:%5d\n", result1, time1, result2, time2);
    }

    static class Solution {
        public int numDistinct(String s, String t) {
            if (t.length() > s.length()) {
                return 0;
            }
            if (t.length() == s.length()) {
                return s.equals(t) ? 1 : 0;
            }
            int[][] cache = new int[s.length()][];
            for (int i = 0; i < cache.length; i++) {
                cache[i] = new int[t.length()];
                Arrays.fill(cache[i], -1);
            }
            return dp(s.length() - 1, t.length() - 1, s.toCharArray(), t.toCharArray(), cache);
        }

        private int dp(int m, int n, char[] s, char[] t, int[][] cache) {
            if (m < n) {
                return 0;
            }

            if (n < 0) {
                return 1;
            }

            if (cache[m][n] != -1) {
                return cache[m][n];
            }

            int res = dp(m - 1, n, s, t, cache);
            if (s[m] == t[n]) {
                res += dp(m - 1, n - 1, s, t, cache);
            }

            return cache[m][n] = res;
        }
    }

    static class Solution_1 {
        public int numDistinct(String s, String t) {
            int n = s.length(), m = t.length();
            int[][] memo = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    memo[i][j] = -1; // -1 表示没有计算过
                }
            }
            return dfs(n - 1, m - 1, s.toCharArray(), t.toCharArray(), memo);
        }

        private int dfs(int i, int j, char[] s, char[] t, int[][] memo) {
            if (i < j) {
                return 0;
            }
            if (j < 0) {
                return 1;
            }
            if (memo[i][j] != -1) { // 之前计算过
                return memo[i][j];
            }
            int res = dfs(i - 1, j, s, t, memo);
            if (s[i] == t[j]) {
                res += dfs(i - 1, j - 1, s, t, memo);
            }
            return memo[i][j] = res; // 记忆化
        }
    }

}
