package per.bmy.other;

/**
 * @author xiaobaobao
 * @date 2020/8/27，17:26
 */
public class MinSwapsOne {

    public static void main(String[] args) {
        System.out.println(new MinSwapsOne().minSwapsCouples(new int[]{3, 0, 2, 1}));
    }

    public int minSwapsCouples(int[] row) {
        int times = 0;
        for (int i = row.length - 1; i >= 0; i--) {
            times += cul(row, i);
        }
        return times;
    }

    public int cul(int[] row, int index) {
        int n = row[index];
        if (n != index) {
            int m = row[n];
            row[n] = n;
            row[index] = m;
            return 1 + cul(row, n);
        }
        return 0;
    }

}
