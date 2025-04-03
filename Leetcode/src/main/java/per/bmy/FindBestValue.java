package per.bmy;

/**
 * @author xiaobaobao
 * @date 2020/9/24，23:03
 */
public class FindBestValue {

    public int findBestValue(int[] arr, int target) {
        quickly(arr, 0, arr.length - 1);
        int N = arr.length;
        int sum = 0;
        int d = Integer.MAX_VALUE;
        int p = 0;
        int a;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            int t = sum + (N - 1) * arr[i];
            a = target - t;
            if (a < d) {
                d = a;
                p = arr[i];
            }
        }
        return p;
    }


    private void quickly(int[] array, int min, int max) {
        if (min >= max) {
            return;
        }
        int point = array[min];
        int i = min;
        int j = max;
        while (i < j) {
            while (i < j && array[j] >= point) {
                j--;
            }
            while (i < j && array[i] <= point) {
                i++;
            }
            if (i < j) {
                array[i] = array[i] ^ array[j];
                array[j] = array[i] ^ array[j];
                array[i] = array[i] ^ array[j];
            }
        }
        if (i != min) {
            array[min] = array[i];
            array[i] = point;
        }
        quickly(array, min, i - 1);
        quickly(array, i + 1, max);
    }

    public static void main(String[] args) {
        System.out.println(new FindBestValue().findBestValue(new int[]{3, 4, 9}, 10));
    }
}
