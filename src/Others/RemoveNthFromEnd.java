package Others;

/**
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 */

public class RemoveNthFromEnd {

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		ListNode a = new ListNode(6);
		ListNode p = a;
		for (int i = 5; i > 0; i--) {
			p.next = new ListNode(i);
			p = p.next;
		}
		ListNode listNode = new RemoveNthFromEnd().removeNthFromEnd(a, 3);
		while (listNode != null) {
			System.out.println(listNode.val);
			listNode = listNode.next;
		}
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode gen = new ListNode(-1);
		gen.next = head;
		ListNode con = head;

		ListNode pre = gen;
		ListNode last = gen;
		int count = 1;
		while (con.next != null) {
			con = con.next;
			count++;
			if (count == n) {
				last = last.next;
			} else if (count > n) {
				pre = pre.next;
				last = last.next;
			}
		}
		pre.next = last.next;
		last.next = null;
		return gen.next;
	}
}
