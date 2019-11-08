package CowStory;

import java.util.Scanner;

/**
 * 初始一头大奶牛，生小奶牛第四年也开始生小奶牛。
 */
public class Main {

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
	}

	public static void main(String[] args) {
		new Main();
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
		cow(cow, index + 1, max);
	}

}