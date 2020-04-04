package Others.dynamicPlanning;

public class ClimbStairs {

	public static void main(String[] args) {
		System.out.println(new ClimbStairs().climbStairs(3));
	}

	public int climbStairs(int n) {
		int[] ints = new int[n + 1];
		ints[0] = 1;
		ints[1] = 1;
		for (int i = 2; i <= n; i++) {
			ints[i] = ints[i - 1] + ints[i - 2];
		}
		return ints[n];
	}
}
