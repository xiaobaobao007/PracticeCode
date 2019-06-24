package StringOperator;

/**
 * @author xiaobaobao
 * @date 2019/6/24 16:27
 * <p>
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 输入: s1 = "abc" s2 = "eidbcaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 问题：无法检测出s1含有相同字符
 */
public class Main3 {
    Main3() {
        String s1 = "dabc";
        int size = s1.length();
        char[] array = s1.toCharArray();
        String s2 = "eidbcaooo";
        char[] array2 = s2.toCharArray();
        int[] s1Array = new int[300];
        for (char c : array) {
            s1Array[c] = 1;
        }
        int[] clone = s1Array.clone();
        int i = 0;
        for (char c : array2) {
            if (clone[c] == 1) {
                if (++i == size) {
                    System.out.println("yes");
                    return;
                }
                clone[c] = 0;
            } else {
                i = 0;
                clone = s1Array.clone();
            }
        }
        System.out.println("no");
    }

    public static void main(String[] args) {
        new Main3();
    }
}
