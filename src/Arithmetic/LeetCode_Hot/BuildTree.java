package Arithmetic.LeetCode_Hot;

import Util.CommonUtil;

/**
 * <pre>
 *     根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author xiaobaobao
 * @date 2020/10/6，17:04
 */
public class BuildTree {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			return "【" +
						   "V=" + val +
						   (left != null ? "，L=" + left : "") +
						   (right != null ? "，R=" + right : "") +
						   '】';
		}
	}

	public static void main(String[] args) {
		System.out.println(new BuildTree().buildTree(CommonUtil.coverString2OneInt("[1,2,3]"), CommonUtil.coverString2OneInt("[1,2,3]")));
	}

	private int preIndex = 0;
	private int inIndex = 0;
	private int[] preArray;
	private int[] inArray;

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		this.preArray = preorder;
		this.inArray = inorder;
		return dfs(null);
	}

	public TreeNode dfs(TreeNode finish) {
		if (preIndex == preArray.length || (finish != null && inArray[inIndex] == finish.val)) {
			return null;
		}
		TreeNode root = new TreeNode(preArray[preIndex++]);
		root.left = dfs(root);
		inIndex++;
		root.right = dfs(finish);
		return root;
	}

}
