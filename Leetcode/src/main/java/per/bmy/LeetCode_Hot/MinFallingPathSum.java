package per.bmy.LeetCode_Hot;

import per.bmy.util.CommonUtil;

/**
 * @descript:
 * @author: bao meng yang <932824098@qq.com>
 * @create: 2021-08-13 16:41
 */
public class MinFallingPathSum {
    public static void main(String[] args) {
        System.out.println(new MinFallingPathSum().minFallingPathSum(CommonUtil.coverString2TwoInt("[[2,1,3],[6,5,4],[7,8,9]]")));
    }

    public int minFallingPathSum(int[][] matrix) {
        int h = matrix.length;
        int w = matrix[0].length;

        int min;
        for (int i = 1; i < h; i++) {
            for (int j = 0; j < w; j++) {
                min = Integer.MAX_VALUE;
                for (int k = Math.max(j - 1, 0), times = 0; k < Math.min(j + 2, w) && times < 3; k++, times++) {
                    min = Math.min(min, matrix[i - 1][k]);
                }
                matrix[i][j] += min;
            }
        }

        min = matrix[h - 1][0];
        for (int i = 1; i < w; i++) {
            if (min > matrix[h - 1][i]) {
                min = matrix[h - 1][i];
            }
        }

        return min;
    }

}
