package per.bmy;

/**
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * <p>
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 * <p>
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MyAtoi {

    public static void main(String[] args) {
        System.out.println(new MyAtoi().myAtoi("++2 3411 asd"));
    }

    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }
        // 偷懒做法，去掉空格，也可以用while循环来做
        String temp = str.trim();
        if ("".equals(temp) || temp.length() == 0) {
            return 0;
        }
        boolean flag = true;
        char[] bits = temp.toCharArray();
        int i = 0;
        int res = 0;
        int bit;
        if (bits[0] == '-') {
            flag = false;
        }
        if (bits[0] == '+' || bits[0] == '-') {
            i++;
        }
        for (; i < bits.length; i++) {
            if (bits[i] >= '0' && bits[i] <= '9') {
                bit = bits[i] - '0';
            } else {
                break;
            }
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && bit > 7)) {
                return flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + bit;
        }
        return flag ? res : -res;
    }
}

