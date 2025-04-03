package per.bmy._150_InterviewClassic;

import java.util.Arrays;

/**
 * <pre>
 *     88. 合并两个有序数组
 *     已解答
 *     简单
 *     相关标签
 *     相关企业
 *     提示
 *     给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 *
 *     请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 *
 *     注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 *
 *
 *
 *     示例 1：
 *
 *     输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 *     输出：[1,2,2,3,5,6]
 *     解释：需要合并 [1,2,3] 和 [2,5,6] 。
 *     合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 *     示例 2：
 *
 *     输入：nums1 = [1], m = 1, nums2 = [], n = 0
 *     输出：[1]
 *     解释：需要合并 [1] 和 [] 。
 *     合并结果是 [1] 。
 *     示例 3：
 *
 *     输入：nums1 = [0], m = 0, nums2 = [1], n = 1
 *     输出：[1]
 *     解释：需要合并的数组是 [] 和 [1] 。
 *     合并结果是 [1] 。
 *     注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
 *
 * </pre>
 *
 * <pre>
 *
 *     应该倒叙插入！！！！
 *
 * </pre>
 *
 * @author baomengyang
 * @date 2025/4/3 16:07:38
 */
public class _88_Merge {
    public static void main(String[] args) {
        test(new int[]{0, 0, 0, 0, 0}, 0, new int[]{1, 2, 3, 4, 5}, 5);
        test(new int[]{2, 0}, 1, new int[]{1}, 1);
        test(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
    }

    static void test(int[] array, int m, int[] nums2, int n) {
        new MySolution().merge(array, m, nums2, n);
        System.out.println(Arrays.toString(array));
    }

    static class MySolution {
        public void merge(int[] array, int p, int[] nums2, int q) {
            int m = 0, n = 0, i, k;
            while (n < q) {
                if (array[m] <= array[n]) {
                    m++;
                    continue;
                }
                k = array[n];
                for (i = n; i > m; i--) {
                    array[i] = array[i - 1];
                }
                array[m] = k;
                m++;
                n++;
            }
        }
    }

    class BeautifulSolution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int p1 = m - 1;
            int p2 = n - 1;
            int p = m + n - 1;
            while (p2 >= 0) { // nums2 还有要合并的元素
                // 如果 p1 < 0，那么走 else 分支，把 nums2 合并到 nums1 中
                if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                    nums1[p--] = nums1[p1--]; // 填入 nums1[p1]
                } else {
                    nums1[p--] = nums2[p2--]; // 填入 nums2[p1]
                }
            }
        }
    }
}