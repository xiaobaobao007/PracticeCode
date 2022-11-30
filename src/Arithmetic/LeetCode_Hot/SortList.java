package Arithmetic.LeetCode_Hot;

/**
 * 148. 排序链表
 * https://leetcode.cn/problems/sort-list/
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 * @descript:
 * @author: baomengyang <baomengyang@sina.cn>
 * @create: 2022-11-29 11:12
 */
public class SortList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }

    public static void main(String[] args) {
        ListNode next = new ListNode(
                4, new ListNode(
                2, new ListNode(
                1, new ListNode(
                3))));

        ListNode sort = new SortList().sortList(next);
        while (sort != null) {
            System.out.println(sort.val);
            sort = sort.next;
        }
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode operation = head;
        while (operation.next != null) {
            if (operation.next.val < operation.val) {
                ListNode sort = removeNext(operation);

                if (sort.val <= head.val) {
                    sort.next = head;
                    head = sort;
                } else {
                    add(head, sort);
                }
            } else {
                operation = operation.next;
            }
        }

        return head;
    }

    private ListNode removeNext(ListNode node) {
        ListNode remove = node.next;
        node.next = remove.next;
        return remove;
    }

    // head -> head.next
    private void add(ListNode head, ListNode add) {
        while (add.val > head.next.val) {
            head = head.next;
        }

        add.next = head.next;
        head.next = add;
    }
}
