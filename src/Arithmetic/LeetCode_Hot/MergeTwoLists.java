package Arithmetic.LeetCode_Hot;

/**
 * <pre>
 *
 *      将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 *  
 *                      示例 1：
 *
 *
 *                      输入：l1 = [1,2,4], l2 = [1,3,4]
 *                      输出：[1,1,2,3,4,4]
 *                      示例 2：
 *
 *                      输入：l1 = [], l2 = []
 *                      输出：[]
 *                      示例 3：
 *
 *                      输入：l1 = [], l2 = [0]
 *                      输出：[0]
 *                       
 *
 *                      提示：
 *
 *                      两个链表的节点数目范围是 [0, 50]
 *                      -100 <= Node.val <= 100
 *                      l1 和 l2 均按 非递减顺序 排列
 *
 *                      来源：力扣（LeetCode）
 *                      链接：https://leetcode.cn/problems/merge-two-sorted-lists
 *
 * </pre>
 *
 * @descript:
 * @author: baomengyang <baomengyang@sina.cn>
 * @create: 2022-12-06 10:25
 */
public class MergeTwoLists {
    public static void main(String[] args) {
        {
            MergeTwoLists.ListNode a = new MergeTwoLists.ListNode(
                    1, new MergeTwoLists.ListNode(
                    2, new MergeTwoLists.ListNode(
                    4)));

            MergeTwoLists.ListNode b = new MergeTwoLists.ListNode(
                    1, new MergeTwoLists.ListNode(
                    3, new MergeTwoLists.ListNode(
                    4)));


            ListNode sort = new Solution1().mergeTwoLists(b, a);
            while (sort != null) {
                System.out.println(sort.val);
                sort = sort.next;
            }
        }

        System.out.println("============================================");

        {
            MergeTwoLists.ListNode a = new MergeTwoLists.ListNode(
                    1, new MergeTwoLists.ListNode(
                    2, new MergeTwoLists.ListNode(
                    4)));

            MergeTwoLists.ListNode b = new MergeTwoLists.ListNode(
                    1, new MergeTwoLists.ListNode(
                    3, new MergeTwoLists.ListNode(
                    4)));


            ListNode sort = new Solution2().mergeTwoLists(b, a);
            while (sort != null) {
                System.out.println(sort.val);
                sort = sort.next;
            }
        }
    }

    static class Solution1 {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }

            ListNode head;
            if (list1.val > list2.val) {
                head = list2;
                list2 = list1;
                list1 = head;
            } else {
                head = list1;
            }

            ListNode insert;

            //list1 head
            while (list2 != null) {
                if (list1.next == null) {
                    list1.next = list2;
                    break;
                }

                if (list2.val < list1.next.val) {
                    insert = list2;

                    list2 = list2.next;

                    insert.next = list1.next;
                    list1.next = insert;
                    list1 = insert;
                } else {
                    list1 = list1.next;
                }
            }

            return head;
        }
    }

    static class Solution2 {
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

    static class ListNode {
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

    }
}
