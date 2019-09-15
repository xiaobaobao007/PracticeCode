package Others;

import java.util.Arrays;

/**
 * 把一个数分成多个整数，如4=1,3;2,2;1,1,2;1,1,1,1
 *
 * @author xiaobaobao
 * @date 2019/9/15，16:33
 */
public class OneCutToMany {

    public static OneCutToMany me = new OneCutToMany();

    public static void main(String[] args) {
        me.toMany(6);
    }

    public void toMany(int num) {
        for (int i = 2; i <= num; i++) {
            int[] ints = new int[i];
            for (int j = 0; j < i - 1; j++) {
                ints[j] = 1;
            }
            ints[i - 1] = num - i + 1;
            System.out.println(Arrays.toString(ints));
            doInts(ints);
        }
    }

    public void doInts(int[] ints) {
        for (int i = ints.length - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (transInts(ints, i, j)) {
                    System.out.println(Arrays.toString(ints));
                    doInts(ints);
                } else {
                    break;
                }
            }
        }
    }

    public boolean transInts(int[] ints, int from, int to) {
        if (ints[from] <= 1) {
            return false;
        }
        ints[from]--;
        ints[to]++;
        if (intsCanContinue(ints)) {
            return true;
        }
        ints[from]++;
        ints[to]--;
        return false;
    }


    public boolean intsCanContinue(int[] ints) {
        for (int i = 0; i < ints.length - 1; i++) {
            if (ints[i] > ints[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
