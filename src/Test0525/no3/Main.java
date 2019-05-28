package Test0525.no3;

import java.util.Scanner;

public class Main {

    int[] map = new int[120];
    int[][] dp = new int[120][120];
    int conNum;
    int allConins;
    int min = Integer.MAX_VALUE;

    Main() {
        Scanner scanner = new Scanner(System.in);
        int testNums = scanner.nextInt();
        while (testNums-- > 0) {
            allConins = 0;
            conNum = scanner.nextInt();
            for (int i = 0; i < conNum; i++) {
                map[i] = scanner.nextInt();
            }
            System.out.println(min);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
    }

    public void battle(int l, int r) {
        if (l >= 0 && r < conNum && l < r) {
            for (int i = l; i <= r; i++) {
                int coins = addConins(i);
                int now = map[i];
                map[i] = 0;
                allConins += coins;
                battle(l, i - 1);
                battle(i + 1, r);
                if (min>allConins) min = allConins;
                allConins -= coins;
                map[i] = now;
            }
        }
    }

    public int addConins(int index) {
        int num = 0;
        if (index - 1 >= 0 && map[index - 1] != 0) num++;
        if (index + 1 < conNum && map[index + 1] != 0) num++;
        return map[index] * num;
    }

}
