package per.bmy.other;

import java.util.Arrays;

/**
 * 给定一个正整数和负整数组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
 * <p>
 * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。
 * <p>
 * 注意：本题相对书上原题稍作改动
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *    [-1,0],
 *    [0,-1]
 * ]
 * 输出: [0,1,0,1]
 * 解释: 输入中标粗的元素即为输出所表示的矩阵
 * 说明：
 * <p>
 * 1 <= matrix.length, matrix[0].length <= 200
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-submatrix-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author xiaobaobao
 * @date 2020/9/18，22:05
 */
public class GetMaxMatrix {
    public static void main(String[] args) {
        int[][] a = {
                {-5},
                {5}
        };
        System.out.println(Arrays.toString(new GetMaxMatrix().getMaxMatrix(a)));
    }

    public int[] getMaxMatrix(int[][] matrix) {
        int[] ans = new int[4];
        int N = matrix.length;
        int M = matrix[0].length;
        int[] b = new int[M];
        int sum;
        int maxsum = Integer.MIN_VALUE;
        int bestr1 = 0;
        int bestc1 = 0;
        // 行的开始序列
        for (int i = 0; i < N; i++) {
            // 重置
            for (int t = 0; t < M; t++) {
                b[t] = 0;
            }
            // 行的结束位置
            for (int j = i; j < N; j++) {
                sum = 0;
                // 开始遍历列
                for (int k = 0; k < M; k++) {
                    b[k] += matrix[j][k];
                    if (sum > 0) {
                        sum += b[k];
                    } else {
                        sum = b[k];
                        bestr1 = i;
                        bestc1 = k;
                    }
                    if (sum > maxsum) {
                        maxsum = sum;
                        ans[0] = bestr1;
                        ans[1] = bestc1;
                        ans[2] = j;
                        ans[3] = k;
                    }
                }
            }
        }
        return ans;
    }
}
