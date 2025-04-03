package per.bmy.other;

import java.util.Arrays;

/**
 * 必须原地修改，只允许使用额外常数空间。
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class NextPermutation {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new NextPermutation().nextPermutation(new int[]{1, 3, 2})));
    }

    public int[] nextPermutation(int[] nums) {
        for (int l = nums.length - 2; l >= 0; l--) {
            for (int r = nums.length - 1; r > l; r--) {
                if (nums[r] > nums[l]) {
                    int a = nums[r];
                    nums[r] = nums[l];
                    nums[l] = a;
                    Arrays.sort(nums, l + 1, nums.length);
                    return nums;
                }
            }
        }
        Arrays.sort(nums);
        return nums;
    }

}