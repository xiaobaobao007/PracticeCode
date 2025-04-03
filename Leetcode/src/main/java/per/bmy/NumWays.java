package per.bmy;

/**
 * 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
 * <p>
 * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
 * <p>
 * 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
 * <p>
 * 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：steps = 3, arrLen = 2
 * 输出：4
 * 解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
 * 向右，向左，不动
 * 不动，向右，向左
 * 向右，不动，向左
 * 不动，不动，不动
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumWays {

    public static void main(String[] args) {
        System.out.println(new NumWays().numWays(3, 2));
    }

    private static int MOD = 1_000_000_007;

    public int numWays(int steps, int arrLen) {
        int[][] dp = new int[steps + 1][steps + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= Math.min(i, arrLen - 1); j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= 1) {
                    dp[i][j] += dp[i - 1][j - 1];
                    dp[i][j] %= MOD;
                }
                if (j < i) {
                    dp[i][j] += dp[i - 1][j + 1];
                    dp[i][j] %= MOD;
                }
            }
        }
        return dp[steps][0];
    }
}