package Others;

/**
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */

public class ListNumberAdd {

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	ListNumberAdd() {
		ListNode a = new ListNode(5);
//		a.next = new ListNode(2);
//		a.next.next = new ListNode(3);

		ListNode b = new ListNode(6);
//		b.next = new ListNode(1);
//		b.next.next = new ListNode(1);

		ListNode listNode = addTwoNumbers(a, b);
		while (listNode != null) {
			System.out.println(listNode.val);
			listNode = listNode.next;
		}
	}

	public static void main(String[] args) {
		new ListNumberAdd();
	}

	public ListNode addTwoNumbers(ListNode a, ListNode b) {
		ListNode sum = new ListNode(0);
		ListNode q = sum;
		ListNode pre = null;
		while (a != null || b != null) {
			int x = a == null ? 0 : a.val;
			int y = b == null ? 0 : b.val;
			int z = q.val + x + y;
			int yu = z / 10;
			q.val = z % 10;
			q.next = new ListNode(yu);
			if (a != null) {
				a = a.next;
			}
			if (b != null) {
				b = b.next;
			}
			pre = q;
			q = q.next;
		}
		if (q.val == 0 && pre != null) {
			pre.next = null;
		}
		return sum;
	}

}
