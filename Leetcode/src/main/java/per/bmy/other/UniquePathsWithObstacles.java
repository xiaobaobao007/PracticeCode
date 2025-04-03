package per.bmy.other;

/**
 * 路径有多少条,并有障碍物
 *
 * @author xiaobaobao
 * @date 20/3/4，13:07
 */
public class UniquePathsWithObstacles {

    public static void main(String[] args) {
        int[][] map = new int[2][1];
        map[0][1] = 1;
        System.out.println(new UniquePathsWithObstacles().uniquePathsWithObstacles(map));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int y = obstacleGrid.length;
        int x = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[y - 1][x - 1] == 1) {
            return 0;
        }
        int[] log = new int[x];
        log[0] = 1;
        for (int i = 0; i < y; i++) {
            int[] ints = obstacleGrid[i];
            for (int j = 0; j < x; j++) {
                if (ints[j] == 1) {
                    continue;
                }
                if (i > 0 && obstacleGrid[i - 1][j] == 1) {
                    log[j] = 0;
                }
                if (j > 0 && ints[j - 1] != 1) {
                    log[j] += log[j - 1];
                }
            }
        }
        return log[x - 1];
    }

}