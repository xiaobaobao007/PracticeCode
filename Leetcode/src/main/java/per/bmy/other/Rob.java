package per.bmy.other;

import java.util.HashMap;
import org.junit.Test;
import per.bmy.LeetCode_Hot.TreeNode;

/**
 * <pre>
 *          在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 *          计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 *          示例 1:
 *
 *          输入: [3,2,3,null,3,null,1]
 *
 *               3
 *              / \
 *             2   3
 *              \   \
 *               3   1
 *
 *          输出: 7
 *          解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 *
 *          来源：力扣（LeetCode）
 *          链接：https://leetcode-cn.com/problems/house-robber-iii
 *          著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 */
class Solution {

    HashMap<TreeNode, Integer> map = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (map.containsKey(root)) {
            return map.get(root);
        }
        int i = root.val + (root.left == null ? 0 : rob(root.left.right) + rob(root.left.left)) + (root.right == null ? 0 : rob(root.right.right) + rob(root.right.left));
        int j = rob(root.left) + rob(root.right);
        int max = Math.max(i, j);
        map.put(root, max);
        return max;

        // return map.compute(root, (k, v) -> {
        //     if (v == null) {
        //         int i = root.val + (root.left == null ? 0 : rob(root.left.right) + rob(root.left.left)) + (root.right == null ? 0 : rob(root.right.right) + rob(root.right.left));
        //         int j = rob(root.left) + rob(root.right);
        //         return Math.max(i, j);
        //     }
        //     return v;
        // });
    }
}


public class Rob {
    @Test
    public void main() {
        TreeNode t = new TreeNode(3);

        t.left = new TreeNode(2);
        t.right = new TreeNode(3);

        // t.left.left = new TreeNode(200);
        t.left.right = new TreeNode(3);

        // t.right.left = new TreeNode(2);
        t.right.right = new TreeNode(1);

        System.out.println(new Solution().rob(t));
    }
}