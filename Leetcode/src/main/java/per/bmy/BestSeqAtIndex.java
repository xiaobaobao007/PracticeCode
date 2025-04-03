package per.bmy;

/**
 * 有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。
 * 出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。已知马戏团每个人的身高和体重，
 * 请编写代码计算叠罗汉最多能叠几个人。
 *
 * @author xiaobaobao
 * @date 2020年4月4日 20点44分
 */
public class BestSeqAtIndex {

    public static void main(String[] args) {
        int[] a = {65, 70, 56, 75, 60, 68};
        int[] b = {100, 150, 90, 190, 95, 110};
        System.out.println(new BestSeqAtIndex().bestSeqAtIndex(a, b));
    }

    public int bestSeqAtIndex(int[] height, int[] weight) {
        quick(height, weight, 0, height.length - 1);
        return 0;
    }

    public void quick(int[] a, int[] b, int x, int y) {
        if (x >= y) {
            return;
        }
        int min = x;
        int max = y;
        int point = a[min];
        while (x < y) {
            while (x < y && a[y] >= point) {
                y--;
            }
            while (x < y && a[x] <= point) {
                x++;
            }
            if (x < y) {
                swap(a, x, y);
                swap(b, x, y);
            }
        }
        a[min] = a[x];
        a[x] = point;
        swap(b, min, x);
        quick(a, b, min, x - 1);
        quick(a, b, x + 1, max);
    }

    public void swap(int[] a, int q, int p) {
        int b = a[q];
        a[q] = a[p];
        a[p] = b;
    }


}