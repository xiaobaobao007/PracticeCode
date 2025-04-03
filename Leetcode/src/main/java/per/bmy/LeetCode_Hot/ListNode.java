package per.bmy.LeetCode_Hot;

/**
 * @descript:
 * @author: baomengyang <baomengyang@sina.cn>
 * @create: 2022-12-06 10:56
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode getListNode(int... array) {
        ListNode head = null;
        for (int i = array.length - 1; i >= 0; i--) {
            if (head == null) {
                head = new ListNode(array[i]);
            } else {
                head = new ListNode(array[i], head);
            }
        }
        return head;
    }

    @Override
    public String toString() {
        if (next == null) {
            return val + "";
        }
        return val + "-" + next.val;
    }
}