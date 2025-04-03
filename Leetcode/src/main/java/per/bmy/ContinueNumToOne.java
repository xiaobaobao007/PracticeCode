package per.bmy;

/**
 * 比如给定一个数组，里面假设有100个数，然后现在有一个固定的数字200,
 * 那么请问，怎么使用数组里面的任意几个数求和，找到一个求和结果最逼近200的这样一组数？
 *
 * @author xiaobaobao
 * @date 2019/9/15，18:12
 */
public class ContinueNumToOne {

    public static ContinueNumToOne me = new ContinueNumToOne();

    public static void main(String[] args) {
        int f1 = 0, f2 = 0, f3 = 2;
        int M = 5;
        for (int i = 3; i < M; i++) {
            f3 += f2;
            f2 = f1;
            f1 = f3;
            System.out.println(f1 + f2 + f3);
        }
        f1 = 1;
        f2 = 1;
        int f;
        for (int i = 3; i < M; i++) {
            f = f2;
            f2 = f1 + f2;
            f1 = f;
            System.out.println(f2);
        }
    }
}