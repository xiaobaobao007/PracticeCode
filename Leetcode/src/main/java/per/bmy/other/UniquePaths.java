package per.bmy.other;

import java.util.Arrays;

/**
 * 路径有多少条
 *
 * @author xiaobaobao
 * @date 20/3/4，13:07
 */
public class UniquePaths {

    public static void main(String[] args) {
        System.out.println(new UniquePaths().uniquePaths2(3, 2));
    }

    public int uniquePaths1(int m, int n) {
        int[][] map = new int[m + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            map[1][i] = 1;
        }
        for (int i = 1; i <= m; i++) {
            map[i][1] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                map[j][i] = map[j - 1][i] + map[j][i - 1];
            }
        }
        return map[m][n];
    }

    /**
     * 只需要维护一个数组，数组被修改前是另一排的数据
     */
    public int uniquePaths2(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] += cur[j - 1];
            }
        }
        return cur[n - 1];
    }

}