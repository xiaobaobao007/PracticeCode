package Others;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * <p>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {

	public static void main(String[] args) {
		System.out.println(new ThreeSumClosest().threeSumClosest(new int[]{0,0,0}, 1));
	}

	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int abs = Integer.MAX_VALUE;
		int resu = 0;
		for (int i = 0; i < nums.length - 2; i++) {
			int l = i + 1;
			int r = nums.length - 1;
			while (l < r) {
				int result = nums[i] + nums[l] + nums[r];
				if (result == target) {
					return target;
				} else if (result < target) {
					l++;
				} else {
					r--;
				}
				if (abs > Math.abs(result - target)) {
					abs = Math.abs(result - target);
					resu = result;
				}
			}
		}
		return resu;
	}

}