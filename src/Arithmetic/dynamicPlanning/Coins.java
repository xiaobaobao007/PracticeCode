package Arithmetic.dynamicPlanning;

public class Coins {

	Coins(int money, int[] coins) {
		int[] n = new int[money + 1];
//        dynamicPlanning(money, coins, n);
		System.err.println(dynamicPlanning(money, coins, n));
	}

	public static void main(String[] args) {
		int[] coins = {1, 5, 10, 20, 50, 100};
		int money = 26;
		new Coins(money, coins);
	}

	public int dynamicPlanning(int money, int[] coins, int[] n) {
		if (money < 0) return Integer.MAX_VALUE - 1;
		else if (money == 0) return 0;
		if (n[money] == 0) {
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < coins.length; i++) {
				min = Math.min(min, dynamicPlanning(money - coins[i], coins, n) + 1);
			}
			n[money] = min;
			System.out.printf("%-3d:", money);
			for (int i = 1; i <= money; i++) {
				System.out.printf("%3d", n[i]);
			}
			System.out.println();
		}
		return n[money];
	}
}
