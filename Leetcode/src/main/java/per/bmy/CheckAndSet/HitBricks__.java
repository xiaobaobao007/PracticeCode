package per.bmy.CheckAndSet;

import java.util.Arrays;
import per.bmy.util.CommonUtil;

/**
 * @author bao meng yang
 * @date 2021/1/16，14:49
 */
public class HitBricks__ {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new HitBricks__().hitBricks(
                CommonUtil.coverString2TwoInt("[[1,0,0,0],[1,1,1,0]]"),
                CommonUtil.coverString2TwoInt("[[1,0]]"))));
    }

    private static final int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public int[] hitBricks(int[][] grid, int[][] hits) {
        for (int[] hit : hits) {
            grid[hit[0]][hit[1]]--;
        }

        for (int i = 0; i < grid[0].length; i++) {
            dfs(grid, 0, i);
        }

        int[] res = new int[hits.length];
        for (int k = hits.length - 1; k >= 0; k--) {
            int i = hits[k][0];
            int j = hits[k][1];
            if (grid[i][j]++ == 0 && isConnected(grid, i, j)) {
                res[k] = dfs(grid, i, j) - 1;
            }
        }

        return res;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return 0;
        }

        grid[i][j] = 2;
        return 1 + dfs(grid, i + 1, j) +
                dfs(grid, i - 1, j) +
                dfs(grid, i, j - 1) +
                dfs(grid, i, j + 1);
    }

    private boolean isConnected(int[][] grid, int x, int y) {
        if (x == 0) {
            return true;
        }

        for (int[] dir : dirs) {
            int i = x + dir[0];
            int j = y + dir[1];
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 2) {
                continue;
            }
            return true;
        }

        return false;
    }

}
