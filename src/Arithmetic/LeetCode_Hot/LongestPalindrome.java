package Arithmetic.LeetCode_Hot;

/**
 * @descript:
 * @author: baomengyang <baomengyang@sina.cn>
 * @create: 2022-12-01 16:11
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("1234234"));
    }

    static class Solution {
        public String longestPalindrome(String s) {
            int start = 0;
            int end = 1;

            for (int i = 1; i < s.length(); i++) {
                for (int j = 1; ; j++) {
                    if (i - j < 0 || i + j >= s.length()) {
                        break;
                    }
                }
            }

            return s.substring(start, end);
        }
    }

}

