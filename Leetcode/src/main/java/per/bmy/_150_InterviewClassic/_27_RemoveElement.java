package per.bmy._150_InterviewClassic;

import java.util.Arrays;
import per.bmy.util.CommonUtil;

/**
 * <pre>
 *
 * 27. 移除元素
 * 简单
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。元素的顺序可能发生改变。然后返回 nums 中与 val 不同的元素的数量。
 *
 * 假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：
 *
 * 更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。nums 的其余元素和 nums 的大小并不重要。
 * 返回 k。
 * 用户评测：
 *
 * 评测机将使用以下代码测试您的解决方案：
 *
 * int[] nums = [...]; // 输入数组
 * int val = ...; // 要移除的值
 * int[] expectedNums = [...]; // 长度正确的预期答案。
 *
 * </pre>
 *
 * @author baomengyang
 * @date 2025/4/3 16:56:11
 */
public class _27_RemoveElement {
    public static void main(String[] args) {
        test(" [3,2,2,3]", 3);
    }

    static void test(String a, int val) {
        int[] array = CommonUtil.coverString2OneInt(a);
        int p = new Solution().removeElement(array, val);
        System.out.println(Arrays.toString(Arrays.stream(array).limit(p).toArray()));
    }

    static class Solution {
        public int removeElement(int[] nums, int val) {
            int k = 0;
            for (int x : nums) {
                if (x != val) {
                    nums[k++] = x;
                }
            }
            return k;
        }
    }
}
