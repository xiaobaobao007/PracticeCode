package per.bmy.LeetCode_Middle;

import per.bmy.util.CommonUtil;

/**
 * <pre>
 *
 *                                    例 1：
 *
 *                                    输入：nums = [2,3,1,1,4]
 *                                    输出：true
 *                                    解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *                                    示例 2：
 *
 *                                    输入：nums = [3,2,1,0,4]
 *                                    输出：false
 *                                    解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 * </pre>
 *
 * <pre>
 *     总结：自己纯考虑动态规划、未考虑最优解
 * </pre>
 *
 * @author baomengyang
 * @date 2025/4/2 16:17:40
 */
public class _55_CanJump {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.canJump(CommonUtil.coverString2OneInt("[2,3,1,1,4]")));
        System.out.println(solution.canJump(CommonUtil.coverString2OneInt("[3,2,1,0,4]")));
    }
}

class Solution {
    public boolean canJump(int[] nums) {
        return jump(nums, 0);
    }

    private boolean jump(int[] nums, int index) {
        if (index == nums.length - 1) {
            return true;
        }
        if (nums[index] == 0) {
            return false;
        }
        for (int i = 1; i <= nums[index]; i++) {
            if (jump(nums, index + i)) {
                return true;
            }
        }
        nums[index] = 0;
        return false;
    }
}

class Solution_super {
    public boolean canJump(int[] nums) {
        int mx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > mx) { // 无法到达 i
                return false;
            }
            mx = Math.max(mx, i + nums[i]); // 从 i 最右可以跳到 i + nums[i]
        }
        return true;
    }
}