package Others;

public class PrimeNumber {
    public static void main(String[] args) {
        prime();
    }

    public static void prime() {
        int i, j, N = 100;
        int[] prime = new int[N + 1];
        for (i = 2; i * i <= N; i++) {
            if (prime[i] == 0) {
                for (j = 2 * i; j <= N; j++) {
                    if (j % i == 0) {
                        prime[j] = 1;
                    }
                }
            }
        }
        for (i = 2; i < N; i++) {
            if (prime[i] == 0) {
                System.out.println(i);
            }
        }
    }
}
