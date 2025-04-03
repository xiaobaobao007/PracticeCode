package per.bmy.LeetCode_Hot;

/**
 * <pre>
 *
 *       25. K 个一组翻转链表
 *
 *       给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 *
 *       k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 *       你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * </pre>
 *
 * @descript:
 * @author: baomengyang <baomengyang@sina.cn>
 * @create: 2022-12-08 16:01
 */
public class _25_reverseKGroup {
    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            int[] array = new int[i];
            for (int j = 0; j < i; j++) {
                array[j] = j + 1;
            }

            ListNode head = ListNode.getListNode(array);

            ListNode sort = new Solution().reverseKGroup(head, 3);
            while (sort != null) {
                System.out.printf("%d ", sort.val);
                sort = sort.next;
            }
            System.out.println();
        }
    }

    static class Solution {
        public ListNode reverseKGroup(ListNode node, int k) {
            if (k == 1) {
                return node;
            }
            ListNode head = null;
            ListNode tail = null;

            ListNode search;
            ListNode nextDoing;

            while (node != null) {
                search = node;
                for (int i = 1; i < k; i++) {
                    if ((search = search.next) == null) {
                        return head == null ? node : head;
                    }
                }

                nextDoing = search.next;
                search.next = null;

                ListNode reverseHead = reverse(node);

                if (head == null) {
                    head = reverseHead;
                } else {
                    tail.next = reverseHead;
                }
                tail = node;
                node.next = nextDoing;
                node = nextDoing;
            }
            return head;
        }

        public ListNode reverse(ListNode head) {
            ListNode revertHead = head;
            head = head.next;
            ListNode next;

            while (head != null) {
                next = head.next;
                head.next = revertHead;
                revertHead = head;
                head = next;
            }

            return revertHead;
        }
    }
}
