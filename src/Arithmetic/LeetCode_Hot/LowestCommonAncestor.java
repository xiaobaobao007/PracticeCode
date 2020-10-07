package Arithmetic.LeetCode_Hot;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Test;
//37,
// -34,-48,
// null,-100,-101,48,
// null,null,null,null,-54,null,-71,-22,

/**
 * <pre>
 *     给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author xiaobaobao
 * @date 2020/10/6，22:22
 */
public class LowestCommonAncestor {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	@Test
	public void main() {

		TreeNode a;
		TreeNode b;

		TreeNode root = new TreeNode(3);

		root.left = new TreeNode(5);
		root.right = new TreeNode(1);

		root.left.left = new TreeNode(6);
		b = root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(0);
		root.right.right = new TreeNode(8);

		a = root.left.right.left = new TreeNode(7);
		root.left.right.right = new TreeNode(4);

		System.out.println(lowestCommonAncestor(root, a, b).val);
		System.out.println(lowestCommonAncestor(root, b, a).val);
	}

	Deque<TreeNode> queue1 = new LinkedList<>();
	Deque<TreeNode> queue2 = new LinkedList<>();

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		find(root, p, queue1);
		find(root, q, queue2);
		// queue1.forEach((a) -> System.out.print(a.val + "_"));
		// System.out.println();
		// queue2.forEach((a) -> System.out.print(a.val + "_"));
		// System.out.println();
		TreeNode node = null;
		Iterator<TreeNode> iterator1 = queue1.iterator();
		Iterator<TreeNode> iterator2 = queue2.iterator();
		while (iterator1.hasNext() && iterator2.hasNext()) {
			TreeNode next1 = iterator1.next();
			TreeNode next2 = iterator2.next();
			if (next1 == next2) {
				node = next1;
			} else {
				break;
			}
		}
		return node;
	}

	public boolean find(TreeNode root, TreeNode p, Deque<TreeNode> queue) {
		queue.addLast(root);
		if (root == p) {
			return true;
		}
		if (root.left != null) {
			if (!find(root.left, p, queue)) {
				queue.removeLast();
			} else {
				return true;
			}
		}
		if (root.right != null) {
			if (!find(root.right, p, queue)) {
				queue.removeLast();
			} else {
				return true;
			}
		}
		return false;
	}
}
