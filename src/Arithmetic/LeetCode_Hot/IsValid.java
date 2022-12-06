package Arithmetic.LeetCode_Hot;

import java.util.LinkedList;

/**
 * @descript:
 * @author: baomengyang <baomengyang@sina.cn>
 * @create: 2022-12-06 10:12
 */
public class IsValid {
    public static void main(String[] args) {
        System.out.println(new Solution().isValid("]"));
    }

    static class Solution {
        LinkedList<Character> stank = new LinkedList<>();

        public boolean isValid(String s) {
            for (int i = 0; i < s.length(); i++) {
                switch (s.charAt(i)) {
                    case '(':
                        push(')');
                        break;
                    case '[':
                        push(']');
                        break;
                    case '{':
                        push('}');
                        break;
                    default:
                        if (stank.isEmpty() || pop() != s.charAt(i)) {
                            return false;
                        }
                }
            }
            return stank.isEmpty();
        }

        private void push(char c) {
            stank.addFirst(c);
        }

        private char pop() {
            return stank.removeFirst();
        }
    }
}
