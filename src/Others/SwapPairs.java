package Others;

/**
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */

public class SwapPairs {

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	SwapPairs() {
		ListNode a = new ListNode(1);
		ListNode p = a;
		for (int i = 2; i < 8; i++) {
			p.next = new ListNode(i);
			p = p.next;
		}
		ListNode listNode = swapPairs(a);
		while (listNode != null) {
			System.out.println(listNode.val);
			listNode = listNode.next;
		}
	}

	public static void main(String[] args) {
		new SwapPairs();
	}

	public ListNode swapPairs(ListNode head) {
		ListNode p = head;
		while (p != null && p.next != null) {
			int a = p.val;
			p.val = p.next.val;
			p.next.val = a;
			p = p.next.next;
		}
		return head;
	}

}
