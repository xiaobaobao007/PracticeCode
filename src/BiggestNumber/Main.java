package BiggestNumber;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    static BigDecimal min = new BigDecimal("0");
    static BigDecimal bigDecimal = new BigDecimal("0");
    static int a;
    static int b;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        a = scanner.nextInt();
        b = scanner.nextInt();
        int[][][] nums = new int[10][a * b][2];
        int[] num = new int[10];
        int max = 0;
        int[][] map = new int[a][b];
        String[] s = new String[a+1];
        scanner.nextLine();
        for (int i = 0; i < a + 1; i++) {
            s[i] = scanner.nextLine();
        }
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                char c = s[i].charAt(j);
                if (c != '#') {
                    max++;
                    int n = c - '0';
                    map[i][j] = n;
                    nums[n][num[n]][0] = i;
                    nums[n][num[n]][1] = j;
                    num[n]++;
                } else {
                    map[i][j] = -1;
                }
            }
        }
        for (int i = 9; i >= 1; i--) {
            for (int j = 0; j < num[i]; j++) {
                start(max, nums[i][j][0], nums[i][j][1], map);
            }
            if (!bigDecimal.equals(min)) {
                System.out.println(bigDecimal.toString());
                break;
            }
        }
    }

    public static void start(int max, int y, int x, int[][] map) {
        get(0, max, y, x, map, new int[max][max], new StringBuffer());
    }

    public static void get(int n, int max, int y, int x, int[][] map, int[][] l, StringBuffer s) {
        if (y < 0 || x < 0 || y >= a || x >= b) {
            return;
        }
        if (n == max) {
            BigDecimal a = new BigDecimal(s.toString());
            if (a.compareTo(bigDecimal) > 0) {
                bigDecimal = a;
            }
        } else if (map[y][x] != -1 && l[y][x] == 0) {
            s.append(map[y][x]);
            l[y][x] = 1;
            get(n + 1, max, y - 1, x, map, l, s);
            get(n + 1, max, y, x - 1, map, l, s);
            get(n + 1, max, y + 1, x, map, l, s);
            get(n + 1, max, y, x + 1, map, l, s);
            s.deleteCharAt(s.length() - 1);
            l[y][x] = 0;
        }
    }
}