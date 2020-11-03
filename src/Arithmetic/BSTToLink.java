package Arithmetic;

import org.junit.Test;

/**
 * 二叉树转中序排列的单链表结构
 */
public class BSTToLink {

	class TreeNode {
		int val;
		TreeNode left, right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	class DoublyListNode {
		int val;
		DoublyListNode next, prev;

		DoublyListNode(int val) {
			this.val = val;
		}
	}

	public DoublyListNode bstToDoublyList(TreeNode root) {
		if (root == null) {
			return null;
		}

		DoublyListNode left = bstToDoublyList(root.left);
		DoublyListNode right = bstToDoublyList(root.right);

		DoublyListNode left_tail = left;
		while (left_tail != null && left_tail.next != null) {
			left_tail = left_tail.next;
		}

		DoublyListNode cur = new DoublyListNode(root.val);
		cur.prev = left_tail;
		if (left_tail != null) {
			left_tail.next = cur;
		}
		cur.next = right;
		if (right != null) {
			right.prev = cur;
		}

		if (left != null) {
			return left;
		} else {
			return cur;
		}
	}

	@Test
	public void main() {
		TreeNode treeNode = new TreeNode(1);

		treeNode.left = new TreeNode(2);
		treeNode.right = new TreeNode(3);

		treeNode.left.left = new TreeNode(4);
		treeNode.left.right = new TreeNode(5);

		treeNode.right.left = new TreeNode(6);
		treeNode.right.right = new TreeNode(7);

		DoublyListNode doublyListNode = bstToDoublyList(treeNode);
		while (doublyListNode != null) {
			System.out.println(doublyListNode.val);
			doublyListNode = doublyListNode.next;
		}
	}

}
