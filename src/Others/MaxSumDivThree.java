package Others;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
 */
public class MaxSumDivThree {

	public static void main(String[] args) {//
		System.out.println(new MaxSumDivThree().maxSumDivThree(new int[]{3, 1, 2, 3, 1, 2}));
	}

	public int maxSumDivThree(int[] nums) {//
		int[] dp = {0, 0, 0};
		for (int i = 0; i < nums.length; i++) {
			int mod = nums[i] % 3;
			int a = dp[(3 - mod) % 3];
			int b = dp[(4 - mod) % 3];
			int c = dp[(5 - mod) % 3];
			if (a > 0 || mod == 0) {
				dp[0] = Math.max(dp[0], a + nums[i]);
			}
			if (b > 0 || mod == 1) {
				dp[1] = Math.max(dp[1], b + nums[i]);
			}
			if (c > 0 || mod == 2) {
				dp[2] = Math.max(dp[2], c + nums[i]);
			}
		}
		return dp[0];
	}

}