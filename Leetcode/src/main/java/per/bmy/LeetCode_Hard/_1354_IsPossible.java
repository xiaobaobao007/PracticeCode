package per.bmy.LeetCode_Hard;

import java.util.Arrays;
import java.util.PriorityQueue;
import per.bmy.util.TimeUtil;

/**
 * <pre>
 *
 *                          1354. 多次求和构造目标数组
 *                          尝试过
 *                          困难
 *                          相关标签
 *                          premium lock icon
 *                          相关企业
 *                          提示
 *                          给你一个整数数组 target 。一开始，你有一个数组 A ，它的所有元素均为 1 ，你可以执行以下操作：
 *
 *                          令 x 为你数组里所有元素的和
 *                          选择满足 0 <= i < target.size 的任意下标 i ，并让 A 数组里下标为 i 处的值为 x 。
 *                          你可以重复该过程任意次
 *                          如果能从 A 开始构造出目标数组 target ，请你返回 True ，否则返回 False 。
 *
 *
 *
 *                          示例 1：
 *
 *                          输入：target = [9,3,5]
 *                          输出：true
 *                          解释：从 [1, 1, 1] 开始
 *                          [1, 1, 1], 和为 3 ，选择下标 1
 *                          [1, 3, 1], 和为 5， 选择下标 2
 *                          [1, 3, 5], 和为 9， 选择下标 0
 *                          [9, 3, 5] 完成
 *                          示例 2：
 *
 *                          输入：target = [1,1,1,2]
 *                          输出：false
 *                          解释：不可能从 [1,1,1,1] 出发构造目标数组。
 *                          示例 3：
 *
 *                          输入：target = [8,5]
 *                          输出：true
 *
 *
 *                          提示：
 *
 *                          N == target.length
 *                          1 <= target.length <= 5 * 10^4
 *                          1 <= target[i] <= 10^9
 *
 * </pre>
 *
 * @author baomengyang
 * @date 2025/12/11 14:23:29
 */
public class _1354_IsPossible {
    public static void main(String[] args) {
        doSolution(new int[]{1});
        doSolution(new int[]{2, 2});
        doSolution(new int[]{2, 900000002});
        doSolution(new int[]{2, 900000001});
        doSolution(new int[]{1, 1000000000});
        doSolution(new int[]{8, 5});
        doSolution(new int[]{9, 3, 5});
        doSolution(new int[]{1, 1, 1, 2});
    }

    private static void doSolution(int[] target) {
        Other_Solution otherSolution = new Other_Solution();
        Solution solution = new Solution();
        Solution_OverStack badSolution = new Solution_OverStack();

        TimeUtil.test(target, otherSolution::isPossible, solution::isPossible, badSolution::isPossible);
    }

    /**
     * 逆序、反向破解
     */
    static class Other_Solution {
        public boolean isPossible(int[] target) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            long sum = 0;
            for (int x : target) {
                pq.offer(x);
                sum += x;
            }

            // 如果最大值等于 1，说明所有数都等于 1
            while (pq.peek() > 1) {
                int x = pq.poll();
                sum -= x; // 减去 x 后，sum 为其余元素之和

                // sum 不能是 0，这意味着 target 只有一个数且这个数大于 1
                // x 减去 sum 后必须是正数
                if (sum == 0 || x <= sum) {
                    return false;
                }

                // 把 x 多次减去 sum，直到 x <= sum 为止
                // 也就是计算 x%sum，但如果 x%sum == 0 则调整为 sum
                x = (x - 1) % (int) sum + 1;
                sum += x;
                pq.offer(x);
            }

            return true;
        }
    }

    /**
     * 逆序、反向破解
     */
    static class Solution {

        public boolean isPossible(int[] target) {
            if (target.length == 1) {
                return target[0] == 1;
            }

            Arrays.sort(target);

            int sum = 0;
            for (int i = target.length - 1; i >= 0; i--) {
                sum += target[i];
            }

            int maxIndex = target.length - 1;
            int maxValue = target[maxIndex];
            int otherSum;

            while (maxValue > (otherSum = sum - maxValue)) {
                target[maxIndex] = maxValue % otherSum;
                if (target[maxIndex] == 0) {
                    target[maxIndex] = otherSum;
                }

                sum -= maxValue - target[maxIndex];

                if (sum < target.length || target[maxIndex] < 1) {
                    break;
                }

                if (sum == target.length) {
                    return true;
                }

                maxIndex = -1;
                maxValue = Integer.MIN_VALUE;

                for (int i = target.length - 1; i >= 0; i--) {
                    if (target[i] > maxValue) {
                        maxValue = target[i];
                        maxIndex = i;
                    }
                }

                if (maxIndex == -1) {
                    return true;
                }
            }

            return false;
        }
    }

    /**
     * 正序暴力破解
     */
    static class Solution_OverStack {
        private int[] target;

        public boolean isPossible(int[] t) {
            this.target = t;

            int[] source = new int[target.length];

            Arrays.fill(source, 1);

            int sum = target.length;

            return recursion(source, target.length);
        }

        private boolean recursion(int[] source, int sum) {
            int p;
            for (int i = 0; i < target.length; i++) {
                if (sum > target[i]) {
                    continue;
                }

                int oldValue = source[i];
                source[i] = sum;

                //检测下结果是否正确
                if (target[i] == sum && Arrays.equals(source, target)) {
                    return true;
                }

                p = sum - oldValue;
                sum += p;

                if (recursion(source, sum)) {
                    return true;
                }

                sum -= p;
                source[i] = oldValue;
            }

            return false;
        }
    }
}
