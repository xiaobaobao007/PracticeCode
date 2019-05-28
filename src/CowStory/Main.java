package CowStory;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    Main() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            if (a == 0) {
                break;
            }
            int[] cow = new int[4];
            cow[3] = 1;
            cow(cow, 2, a);
        }
//        int[] cow = new int[4];
//        cow[3] = 1;
//        cow(cow, 2, 6);
    }

    public void cow(int[] cow, int index, int max) {
        if (index > max) {
            int num = 0;
            for (int i = 0; i < 4; i++) {
                num += cow[i];
            }
            System.out.println(num);
            return;
        }
        int a = cow[3];
        cow[3] = cow[2];
        cow[2] = cow[1];
        cow[1] = cow[0];
        cow[3] += a;
        cow[0] = cow[3];
//        for (int i = 0; i < 4; i++) {
//            System.out.print(cow[i] + ",");
//        }
//        System.out.println();
        cow(cow, index + 1, max);
    }

}