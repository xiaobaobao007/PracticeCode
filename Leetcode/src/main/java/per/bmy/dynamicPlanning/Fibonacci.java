package per.bmy.dynamicPlanning;

public class Fibonacci {

    Fibonacci(int n) {
        int[] m = new int[n + 1];
        System.err.println(dynamicPlanning(n, m));
    }

    public static void main(String[] args) {
        new Fibonacci(10);
    }

    public int dynamicPlanning(int n, int[] m) {
        if (n == 1 || n == 2) return 1;
        if (m[n] == 0) {
            System.out.println(n);
            m[n] = dynamicPlanning(n - 1, m) + dynamicPlanning(n - 2, m);
        }
        return m[n];
    }
}
