package per.bmy.LeetCode_Hot;

import java.util.Arrays;

/**
 * @author xiaobaobao
 * @date 2020/10/3，21:16
 */
public class NextPermutation {

    public static void main(String[] args) {
        int[] nums = {1, 1, 5};
        new NextPermutation().nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        int j = nums.length - 1;
        int k = j;

        while (i >= 0 && nums[i] >= nums[j]) {
            i--;
            j--;
        }

        if (i >= 0) {
            while (nums[i] >= nums[k]) {
                k--;
            }

            int temp = nums[i];
            nums[i] = nums[k];
            nums[k] = temp;
        }

        int left = j, right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left++] = nums[right];
            nums[right--] = temp;
        }
    }
}
