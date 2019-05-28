package Test0525.no1;

import java.util.Scanner;

public class Main {

    int MAX = 1100;

    int pointNum;
    float[][] point = new float[MAX][2];
    boolean[][] map = new boolean[MAX][MAX];
    float ballL;
    int first;

    Main() {
        Scanner scanner = new Scanner(System.in);
        int testNums = scanner.nextInt();
        while (testNums-- > 0) {
            pointNum = scanner.nextInt();
            int q = pointNum;
            ballL = scanner.nextFloat() * 2;
            float ma = 120;
            while (q > 0) {
                point[q][0] = scanner.nextFloat();
                point[q--][1] = scanner.nextFloat();
                if (point[q][1] < ma) {
                    ma = point[q][1];
                    first = q;
                }
            }
            for (int i = 0; i <= pointNum + 1; i++) {//1 1 25.0 50.0 50.0//1 1 25.0 49.0 50.0//1 3 25.0 20.0 80.0 80.0 80.0 80.0 20.0
                for (int j = 0; j < i; j++) {
                    if (i == j) continue;
                    touch(i, j);
                }
            }
            for (int i = 0; i <= pointNum + 1; i++) {
                for (int j = 0; j < i; j++) {
                    if (i == j) continue;
                    System.out.print(map[i][j] == true ? 1 : 0);
                }
                System.out.println("");
            }

        }
    }

    public static void main(String[] args) {
        Main main = new Main();
    }

    public void touch(int a, int b) {
        if (b == 0) {
            map[a][b] = point[a][0] > ballL;
        } else if (a == pointNum + 1) {
            map[a][b] = point[b][0] + ballL < 100;
        } else {
            map[a][b] = (Math.pow((point[a][0] - point[b][0]), 2) + Math.pow((point[a][1] - point[b][1]), 2)) > Math.pow(ballL, 2);
        }
    }
}
