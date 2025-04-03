package per.bmy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 大括号中括号小括号，
 * 要求
 * 1：一对一对出现
 * 2：中括号不能被小括号包裹
 *
 * @author xiaobaobao
 * @date 2020/11/24，20:39
 */
public class OrderedParenthesis {

    public boolean main(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        char c;
        int q = 0;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            switch (c) {
                case ')':
                    if (!stack.getFirst().equals('(')) {
                        return false;
                    }
                    stack.removeFirst();
                    q = 2;
                    break;
                case ']':
                    if (!stack.getFirst().equals('[')) {
                        return false;
                    }
                    stack.removeFirst();
                    q = 1;
                    break;
                case '}':
                    if (!stack.getFirst().equals('{')) {
                        return false;
                    }
                    stack.removeFirst();
                    q = 0;
                    break;
                case '(':
                    if (q > 2) {
                        return false;
                    }
                    q = 3;
                    stack.addFirst(c);
                    break;
                case '[':
                    if (q > 1) {
                        return false;
                    }
                    q = 2;
                    stack.addFirst(c);
                    break;
                case '{':
                    if (q > 0) {
                        return false;
                    }
                    q = 1;
                    stack.addFirst(c);
                    break;
                default:
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new OrderedParenthesis().main("{[()()]}"));
    }

}