package Arithmetic.CheckAndSet;

import java.util.Arrays;

import Util.CommonUtil;

/**
 * @author bao meng yang
 * @date 2021/1/16，14:49
 */
public class HitBricks {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new HitBricks_().hitBricks(
				CommonUtil.coverString2TwoInt("[[1,0,0,0],[1,1,1,0]]"),
				CommonUtil.coverString2TwoInt("[[1,0]]"))));
	}

	int round = 1;
	int all_num = 0;
	int had_num = 0;
	int[][] xy_operation = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
	int[][] grid;
	int[][] hits;
	int _X;
	int _Y;

	public int[] hitBricks(int[][] grid, int[][] hits) {
		this.grid = grid;
		this.hits = hits;
		_X = grid.length;
		_Y = grid[0].length;

		for (int i = 0; i < _X; i++) {
			for (int j = 0; j < _Y; j++) {
				if (grid[i][j] != 0) {
					all_num++;
				}
			}
		}

		int[] result = new int[hits.length];
		for (int i = 0; i < hits.length; i++) {
			had_num = 0;
			round++;
			if (grid[hits[i][0]][hits[i][1]] == 0) {
				continue;
			} else {
				grid[hits[i][0]][hits[i][1]] = 0;
				all_num--;
			}
			for (int j = 0; j < _Y; j++) {
				do_it(0, j);
			}
			result[i] = Math.max(all_num - had_num, 0);
			all_num = had_num;
		}

		return result;
	}

	private void do_it(int x, int y) {
		if (x < 0 || y < 0 || x >= _X || y >= _Y || grid[x][y] == 0 || grid[x][y] == round) {
			return;
		}
		grid[x][y] = round;
		had_num++;
		for (int[] ints : xy_operation) {
			do_it(x + ints[0], y + ints[1]);
		}
	}
}
