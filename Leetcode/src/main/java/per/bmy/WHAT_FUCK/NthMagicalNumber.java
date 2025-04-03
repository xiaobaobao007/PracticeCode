package per.bmy.WHAT_FUCK;

/**
 * @author xiaobaobao
 * @date 2020/9/24，23:03
 */
public class NthMagicalNumber {

    public int nthMagicalNumber(int N, int A, int B) {
        int MOD = 1_000_000_007;
        int L = A / gcd(A, B) * B;

        long lo = 0;
        long hi = (long) 1e15;
        while (lo < hi) {
            long mi = lo + (hi - lo) / 2;
            if (mi / A + mi / B - mi / L < N)
                lo = mi + 1;
            else
                hi = mi;
        }

        return (int) (lo % MOD);
    }

    public int gcd(int x, int y) {
        if (x == 0) return y;
        return gcd(y % x, x);
    }

    public static void main(String[] args) {
        System.out.println(1_123);
        System.out.println((long) 2e2);
        System.out.println(new NthMagicalNumber().nthMagicalNumber(5, 2, 4));
    }
}
