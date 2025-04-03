package per.bmy.other;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 */
public class FourSumClosest {

    public static void main(String[] args) {
        System.out.println(new FourSumClosest().fourSum(new int[]{-5, -4, -3, -2, -1, 0, 0, 1, 2, 3, 4, 5}, 0));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) return new LinkedList<>();
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j - i > 1 && nums[j] == nums[j - 1]) continue;
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if (nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target) continue;
                int tmp = nums[i] + nums[j];
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    if ((tmp + nums[left] + nums[right]) == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if ((tmp + nums[left] + nums[right]) < target) {
                        left++;
                    } else {
                        right--;
                    }
                }

            }
        }
        return res;
    }
}