package SpiralArry;

import java.util.Scanner;


/**
 * 别人写的,,,代码行数很短,,
 */
public class SpiralArry2 {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            n = 2 * n - 1; //把这行注视掉可以实现任意n*n的螺旋
            int[][] a = new int[n][n];
            for (int i = n * n, j = n - 1, x = 0, y = n; i > 0; ) { //思路1:外环到内环逆时针旋转
                while (y > n - j - 1) a[x][--y] = i--; //右上到左上
                while (x < j) a[++x][y] = i--; //左上到左下
                while (y < j) a[x][++y] = i--; //左下到右下
                while (x > n - j) a[--x][y] = i--; //右下到右上
                j--; //环数递减
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.printf("%3d ", a[i][j]);
                }
                System.out.println();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
