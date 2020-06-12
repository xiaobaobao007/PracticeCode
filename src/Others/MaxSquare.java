package Others;

/**
 * @author xiaobaobao
 * @date 2020/6/11，15:02
 * <p>
 * 给定一个由 0 和 1 组成的二维矩阵 matrix
 * 实现一个算法，找到其中只包含 1 的最大正方形，并返回该正方形的面积。
 */
public class MaxSquare {

	public static void main(String[] args) {
//		int[][] map = {
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0},
//				{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
//				{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
//				{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
//				{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
//				{0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};//max=5


		int[][] map = {
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};//max=8
		System.out.println(new MaxSquare().max(map));
	}

	public int max(int[][] map) {
		int y = map.length;
		if (y == 0) {
			return 0;
		}
		int x = map[0].length;
		int max = 0;

		for (int j = 1; j < y; j++) {
			for (int i = 1; i < x; i++) {
				if (map[j][i] == 1) {
					max = Math.max(map[j][i] = Math.min(map[j][i - 1], Math.min(map[j - 1][i], map[j - 1][i - 1])) + 1, max);
				}
			}
		}

		return max;
	}

}
