package Arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * <p>
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CoversionZ {


	public static void main(String[] args) {
		System.out.println(new CoversionZ().convert("AB", 1));
	}

	public String convert(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}

		List<StringBuilder> rows = new ArrayList<>();
		for (int i = 0; i < Math.min(numRows, s.length()); i++) {
			rows.add(new StringBuilder());
		}

		int n = 0, flag = 1;
		for (int i = 0; i < s.length(); i++) {
			rows.get(n).append(s.charAt(i));
			n += flag;
			if (n == 0 || n == numRows - 1) {
				flag *= -1;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (StringBuilder ss : rows) {
			sb.append(ss);
		}
		return sb.toString();
	}
}
