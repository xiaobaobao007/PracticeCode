package per.bmy.LeetCode_Hot;

/**
 * <pre>
 *
 *     23. 合并K个升序链表
 *            给你一个链表数组，每个链表都已经按升序排列。
 *
 *            请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 *            示例 1：
 *
 *            输入：lists = [[1,4,5],[1,3,4],[2,6]]
 *            输出：[1,1,2,3,4,4,5,6]
 *            解释：链表数组如下：
 *            [
 *              1->4->5,
 *              1->3->4,
 *              2->6
 *            ]
 *            将它们合并到一个有序链表中得到。
 *            1->1->2->3->4->4->5->6
 *            示例 2：
 *
 *            输入：lists = []
 *            输出：[]
 *            示例 3：
 *
 *            输入：lists = [[]]
 *            输出：[]
 *
 *
 *            提示：
 *
 *            k == lists.length
 *            0 <= k <= 10^4
 *            0 <= lists[i].length <= 500
 *            -10^4 <= lists[i][j] <= 10^4
 *            lists[i] 按 升序 排列
 *            lists[i].length 的总和不超过 10^4
 *
 *            https://leetcode.cn/problems/merge-k-sorted-lists/
 *
 *
 * </pre>
 *
 * @descript:
 * @author: baomengyang <baomengyang@sina.cn>
 * @create: 2022-12-06 10:52
 */
public class MergeKLists {

    public static void main(String[] args) {
        ListNode a = new ListNode(
                1);

        ListNode b = new ListNode(
                0);

        ListNode sort = new Solution().mergeKLists(new ListNode[]{a, b});
        while (sort != null) {
            System.out.println(sort.val);
            sort = sort.next;
        }
    }

    static class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            int i = 0;

            ListNode head = null;
            for (; i < lists.length; i++) {
                head = lists[i];
                if (head != null) {
                    break;
                }
            }

            if (head == null) {
                return null;
            }
            i++;

            for (; i < lists.length; i++) {
                head = mergeTwoLists(head, lists[i]);
            }

            return head;
        }

        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }

            if (list1.val < list2.val) {
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            } else {
                list2.next = mergeTwoLists(list1, list2.next);
                return list2;
            }
        }
    }

}
