package per.bmy.LeetCode_Hot;

import org.junit.Test;

/**
 * @author xiaobaobao
 * @date 2020/10/9，17:06
 */
public class Flatten {

    @Test
    public void main() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(2);
        treeNode.right.left = new TreeNode(3);
        flatten(treeNode);
        System.out.println(treeNode);
    }

    TreeNode last = null;

    //从右子节点，从后往前
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = last;
        root.left = null;
        last = root;
    }

}