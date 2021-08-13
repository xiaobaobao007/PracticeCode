package Arithmetic;

import Util.CommonUtil;

/**
 * @descript:
 * @author: bao meng yang <932824098@qq.com>
 * @create: 2021-08-13 17:48
 */
public class OrangesRotting {
    public static void main(String[] args) {
        System.out.println(new OrangesRotting().orangesRotting(CommonUtil.coverString2TwoInt("[[0,2]]")));
    }

    public int orangesRotting(int[][] grid) {
        int h = grid.length;
        int w = grid[0].length;

        int changeId = 2;
        boolean hadChange;
        int freshApples;
        while (true) {
            hadChange = false;
            freshApples = 0;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (grid[i][j] == changeId) {
                        if (j - 1 >= 0) {
                            if (grid[i][j - 1] == 1) {
                                grid[i][j - 1] = changeId + 1;
                                hadChange = true;
                                freshApples--;
                            }
                        }
                        if (j + 1 < w) {
                            if (grid[i][j + 1] == 1) {
                                grid[i][j + 1] = changeId + 1;
                                hadChange = true;
                                freshApples--;
                            }
                        }
                        if (i - 1 >= 0) {
                            if (grid[i - 1][j] == 1) {
                                grid[i - 1][j] = changeId + 1;
                                hadChange = true;
                                freshApples--;
                            }
                        }
                        if (i + 1 < h) {
                            if (grid[i + 1][j] == 1) {
                                grid[i + 1][j] = changeId + 1;
                                hadChange = true;
                                freshApples--;
                            }
                        }
                    } else if (grid[i][j] == 1) {
                        freshApples++;
                    }
                }
            }

            if (!hadChange) {
                break;
            }

            changeId++;
        }

        if (freshApples > 0) {
            return -1;
        }

        return changeId - 2;
    }
}
