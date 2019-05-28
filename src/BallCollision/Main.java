package BallCollision;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int[] num = new int[8];
            boolean out = true;
            for (int i = 0; i < 8; i++) {
                num[i] = scanner.nextInt();
                if (num[i] != 0) out = false;
            }
            if (out) {
                return;
            }
            new Main(num);
        }
    }

    Main(int[] num) {
        int s = num[6] * num[7];//路程
        int r = num[4];
        int L = num[0] - 2*r;
        int W = num[1] - 2*r;
        int x = num[2] - r;
        int y = num[3] - r;
        int a = num[5];
        double x0 = s * Math.cos(Math.PI * a / 180);
        double y0 = s * Math.sin(Math.PI * a / 180);
        double x1 = x0 + x;
        double y1 = y0 + y;
        x1 = after(L, x1, x);
        y1 = after(W, y1, y);
        System.out.printf("%.2f %.2f\n", x1 + r, y1 + r);
    }

    public double after(int L, double x1, double x) {
        if (x1 < 0) {
            x1 = -x1;
        }
        if (x1 / L > 0) {
            int a = (int) (x1 / L);
            x1 -= a * L;
            if ((a & 1) == 1) {
                x1 = L - x1;
            }
        }
        return x1;
    }
}