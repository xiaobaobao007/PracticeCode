package Others.dynamicPlanning;

public class CoinsNum {

	CoinsNum(int money, int[] coins, int[] nums) {
		int[] n = new int[money + 1];
		int[][] m = new int[money + 1][coins.length + 1];
		System.err.println(dynamicPlanning(money, 0, coins, n, nums, m));
	}

	public static void main(String[] args) {
		int[] coins = {1, 5, 10, 20, 50, 100};
		int[] nums = {10, 10, 10, 10, 10, 10};
		int money = 26;
		new CoinsNum(money, coins, nums);
	}

	public int dynamicPlanning(int money, int subMoney, int[] coins, int[] n, int[] nums, int[][] m) {
		int q = money;
		int w = subMoney;
		subMoney = coins[subMoney];
		money -= subMoney;
		if (money < 0 || m[money][w] == nums[w]) return Integer.MAX_VALUE - 1;
		else if (money == 0) return 0;
		if (n[money] == 0) {
			int min = Integer.MAX_VALUE;
			int j = min;
			int num = 0;
			for (int i = 0; i < coins.length; i++) {
				min = Math.min(min, dynamicPlanning(money, i, coins, n, nums, m) + 1);
				if (j != min) {
					num = i;
					j = min;
				}
			}
			m[money][num] = m[money - 1][num] + 1;
			m[money] = compile(m[money], m[money - 1]);
//            for (int i = 1; i < coins.length; i++) {
//                if (m[money][i] < m[money - 1][i]) m[money][i] = m[money - 1][i];
//            }
			n[money] = min;
			System.out.printf("%-3d:", money);
			for (int i = 0; i < coins.length; i++) {
				System.out.printf("%3d", m[money][i]);
			}
			System.out.println();
		}
		return n[money];
	}

	public int[] compile(int[] small, int[] big) {
		int s = 0;
		int b = 0;
		int[] result;
		for (int length = small.length - 1; length >= 0; length--) {
			if (small[length] != 0) {
				s = length;
				break;
			}
		}
		for (int length = big.length - 1; length >= 0; length--) {
			if (big[length] != 0) {
				b = length;
				break;
			}
		}
		if (s > b) {
			big[b] = small[b];
		}
		return big;
	}
}
