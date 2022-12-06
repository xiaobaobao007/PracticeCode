package Arithmetic.LeetCode_Hot;

/**
 * @descript:
 * @author: baomengyang <baomengyang@sina.cn>
 * @create: 2022-12-01 14:42
 */
public class FindMedianSortedArrays {
    public static void main(String[] args) {
        System.out.println(new Solution().findMedianSortedArrays(new int[]{4, 8, 9}, new int[]{3, 6, 9}));
    }

    static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int i = 0;
            int j = 0;

            int pointIndex_1 = nums1.length + nums2.length;
            int pointIndex_2;

            if (pointIndex_1 % 2 == 0) {
                pointIndex_2 = pointIndex_1 / 2;
                pointIndex_1 = pointIndex_2 - 1;
            } else {
                pointIndex_1 = pointIndex_2 = pointIndex_1 / 2;
            }

            int allIndex;
            int pointA;
            int pointB;
            int allNum = 0;
            while (i < nums1.length || j < nums2.length) {
                allIndex = i + j;

                if (i < nums1.length) {
                    pointA = nums1[i];
                } else {
                    pointA = Integer.MAX_VALUE;
                }

                if (j < nums2.length) {
                    pointB = nums2[j];
                } else {
                    pointB = Integer.MAX_VALUE;
                }

                if (pointA > pointB) {
                    if (pointIndex_1 == allIndex) {
                        allNum += nums2[j];
                    }
                    if (pointIndex_2 == allIndex) {
                        allNum += nums2[j];
                    }
                    j++;
                } else {
                    if (pointIndex_1 == allIndex) {
                        allNum += nums1[i];
                    }
                    if (pointIndex_2 == allIndex) {
                        allNum += nums1[i];
                    }
                    i++;
                }

                if (pointIndex_1 <= allIndex && pointIndex_2 <= allIndex) {
                    break;
                }
            }

            return allNum * 0.5D;
        }
    }

}
