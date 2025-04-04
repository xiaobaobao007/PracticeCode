package per.bmy.other;

/**
 * @author xiaobaobao
 * @date 2020/9/24，23:03
 */
public class WaysToChange {

    private final int mod = 1000000007;
    private final int[] coins = {25, 10, 5, 1};

    public int waysToChange(int n) {
        int[] res = new int[n + 1];
        res[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= n; i++) {
                res[i] = (res[i] + res[i - coin]) % mod;
            }
        }
        return res[n];
    }

    public static void main(String[] args) {
        System.out.println(new WaysToChange().waysToChange(5));
    }
}
