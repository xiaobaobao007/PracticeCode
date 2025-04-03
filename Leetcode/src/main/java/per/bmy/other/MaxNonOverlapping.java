package per.bmy.other;

/**
 * @author xiaobaobao
 * @date 2020/9/22，23:22
 */
public class MaxNonOverlapping {

    public static void main(String[] args) {
        new MaxNonOverlapping().maxNonOverlapping(new int[]{-1, 3, 5, 1, 4, 2, -9}, 6);
    }

    int[][] result = null;

    public int maxNonOverlapping(int[] nums, int target) {
        int N = nums.length;
        result = new int[N][N];
        int num;
        for (int i = 0; i < N; i++) {
            num = 0;
            for (int j = i; j < N; j++) {
                if ((num += nums[j]) == target) {
                    result[i][j] = 1;
                }
            }
        }
        return 1;
    }
}
