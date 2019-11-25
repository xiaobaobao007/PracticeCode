package Others;

import java.util.Arrays;

/**
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 */

public class CountBits {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new CountBits().countBits(10)));
	}

//	public int[] countBits(int num) {
//		int[] result = new int[num + 1];
//		int now = 0;
//		int max = 1;
//		result[0] = 0;
//		for (int i = 1; i <= num; i++) {
//			result[i] = result[now++] + 1;
//			if (now >= max) {
//				now = 0;
//				max *= 2;
//			}
//		}
//		return result;
//	}

	//标准答案
	public int[] countBits(int num) {
		int[] res = new int[num + 1];
		for (int i = 1; i <= num; i++) {
			res[i] = res[i & (i - 1)] + 1;
		}
		return res;
	}

}