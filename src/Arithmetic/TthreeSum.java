package Arithmetic;

import java.util.*;

/**
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class TthreeSum {

	public static void main(String[] args) {
//		Set<List<Integer>> set = new HashSet<>();
//		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3));
//		ArrayList<Integer> b = new ArrayList<>(Arrays.asList(1, 2, 3));
//		ArrayList<Integer> c = new ArrayList<>(Arrays.asList(1, 3, 2));
//		set.add(a);
//		System.out.println(set.contains(b));
//		System.out.println(set.contains(c));
//		System.out.println(a == b);

		System.out.println(Arrays.toString(new TthreeSum().threeSum(new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6}).toArray()));
	}

	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		Set<List<Integer>> set = new HashSet<>();
		for (int i = 0; i < nums.length - 2; i++) {
			int l = i + 1;
			int r = nums.length - 1;
			while (l < r) {
				int result = nums[i] + nums[l] + nums[r];
				if (result == 0) {
					ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r]));
					if (set.size() == 0 || !set.contains(arrayList)) {
						set.add(arrayList);
					}
					l++;
				} else if (result < 0) {
					l++;
				} else {
					r--;
				}
			}
		}
		return new ArrayList<>(set);
	}

}