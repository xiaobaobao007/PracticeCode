package StringOperator;

/**
 * @author xiaobaobao
 * @date 2019/6/24 16:07
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class Main2 {

    Main2() {
        String s = "abcabcbb";
        int size = 0;
        int max = 0;
        int[] result = new int[300];
        for (char c : s.toCharArray()) {
            if (result[c] == 0) {
                result[c] = 1;
                if (++size > max) {
                    max = size;
                }
            } else {
                result[c] = 0;
                size--;
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        new Main2();
    }
}
