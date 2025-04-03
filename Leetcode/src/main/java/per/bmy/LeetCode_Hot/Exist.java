package per.bmy.LeetCode_Hot;

import per.bmy.util.CommonUtil;

/**
 * <pre>
 *     给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 			单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 			 
 *
 * 			示例:
 *
 * 			board =
 * 			[
 * 			  ['A','B','C','E'],
 * 			  ['S','F','C','S'],
 * 			  ['A','D','E','E']
 * 			]
 *
 * 			给定 word = "ABCCED", 返回 true
 * 			给定 word = "SEE", 返回 true
 * 			给定 word = "ABCB", 返回 false
 *
 * 			来源：力扣（LeetCode）
 * 			链接：https://leetcode-cn.com/problems/word-search
 * 			著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author xiaobaobao
 * @date 2020/10/4，21:07
 */
public class Exist {
    public static void main(String[] args) {
        System.out.println(new Exist().exist(CommonUtil.coverString2TwoChar("[[\"C\",\"A\",\"A\"]," +
                        "[\"A\",\"A\",\"A\"]," +
                        "[\"B\",\"C\",\"D\"]]"),
                "AAB"));
    }

    int H;
    int W;
    int[][] point = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    int q;
    int p;

    public boolean exist(char[][] board, String word) {
        H = board.length;
        W = board[0].length;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (exist(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean exist(char[][] board, String word, int x, int y, int index) {
        char c = word.charAt(index);
        if (board[x][y] != c) {
            return false;
        } else if (index == word.length() - 1) {
            return true;
        }
        board[x][y] = 0;
        for (int[] ints : point) {
            q = x + ints[0];
            p = y + ints[1];
            if (q >= 0 && p >= 0 && q < H && p < W) {
                if (exist(board, word, q, p, index + 1)) {
                    return true;
                }
            }
        }
        board[x][y] = c;
        return false;
    }
}
