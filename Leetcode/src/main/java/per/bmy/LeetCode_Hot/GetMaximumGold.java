package per.bmy.LeetCode_Hot;

import per.bmy.util.CommonUtil;

/**
 * @descript: 你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。
 * 每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。
 * <p>
 * 为了使收益最大化，矿工需要按以下规则来开采黄金：
 * <p>
 * 每当矿工进入一个单元，就会收集该单元格中的所有黄金。
 * 矿工每次可以从当前位置向上下左右四个方向走。
 * 每个单元格只能被开采（进入）一次。
 * 不得开采（进入）黄金数目为 0 的单元格。
 * 矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
 * <p>
 * 输入：grid = [[0,6,0],[5,8,7],[0,9,0]]
 * 输出：24
 * 解释：
 * [[0,6,0],
 * [5,8,7],
 * [0,9,0]]
 * 一种收集最多黄金的路线是：9 -> 8 -> 7。
 * <p>
 * 输入：grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * 输出：28
 * 解释：
 * [[1,0,7],
 * [2,0,6],
 * [3,4,5],
 * [0,3,0],
 * [9,0,20]]
 * 一种收集最多黄金的路线是：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7。
 * <p>
 * [1,1,1,0],
 * [1,0,1,0],
 * [1,0,0,1],
 * [1,1,1,1],
 *
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-with-maximum-gold
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: bao meng yang <932824098@qq.com>
 * @create: 2021-08-23 09:02
 */
public class GetMaximumGold {
    public static void main(String[] args) {
        String title = "[[1,1,1,0],[1,0,1,0],[1,0,0,1],[1,1,1,1]]";

        System.out.println(new NoCacheSolution().getMaximumGold(CommonUtil.coverString2TwoInt(title)));
        System.out.println(new UseCacheSolution().getMaximumGold(CommonUtil.coverString2TwoInt(title)));
    }

    static class NoCacheSolution {

        int[][] grid;
        int maxHeight;
        int maxWidth;
        int[] dirs = new int[]{0, 1, 0, -1, 0};

        public int getMaximumGold(int[][] grid) {
            this.grid = grid;
            maxHeight = grid.length;
            maxWidth = grid[0].length;

            int max = 0;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < maxWidth; j++) {
                    if (grid[i][j] <= 0) {
                        continue;
                    }

                    max = Math.max(max, search(i, j));
                }
            }

            return max;
        }

        public int search(int height, int width) {
            if (height < 0 || width < 0 || height >= maxHeight || width >= maxWidth || grid[height][width] <= 0) {
                return -1;
            }

            grid[height][width] *= -1;
            int result = 0;
            for (int i = 0; i < 4; i++) {
                result = Math.max(result, search(height + dirs[i], width + +dirs[i + 1]));
            }
            grid[height][width] *= -1;

            if (result < 0) {
                result = 0;
            }
            return result + grid[height][width];
        }
    }

    static class UseCacheSolution {
        int[][] grid, v;
        int m, n = 0;
        int[] dirs = new int[]{0, 1, 0, -1, 0};

        public int getMaximumGold(int[][] grid) {
            int ans = 0;
            m = grid.length;
            n = grid[0].length;
            v = new int[m][n];
            this.grid = grid;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] != 0 && v[i][j] == 0) {
                        ans = Math.max(ans, dfs(i, j));
                    }
                }
            }
            return ans;
        }

        private int dfs(int x, int y) {
            int ans = 0;
            grid[x][y] = -grid[x][y];
            for (int k = 0; k < 4; k++) {
                int i = dirs[k] + x, j = dirs[k + 1] + y;
                if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] > 0) {
                    ans = Math.max(ans, dfs(i, j));
                }
            }
            grid[x][y] = -grid[x][y];

            v[x][y] = ans; // ans 有可能为 0
            return ans + grid[x][y];
        }
    }
}
