package per.bmy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 这写的什么鬼，这么麻烦，数据量这么大,请参考{@link OneCutToMany}
 *
 * @author xiaobaobao
 * @date 2019/8/26 9:09
 */

class Composition extends ArrayList<Integer> {
    @Override
    public boolean equals(Object other) {
        Composition comp = (Composition) other;
        Collections.sort(this);
        Collections.sort(comp);
        if (this.isEmpty() || comp.isEmpty() || this.size() != comp.size())
            return false;
        for (int i = 0; i < this.size(); i++)
            if (this.get(i) != comp.get(i))
                return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

public class OneCutToManyError {
    public static void main(String[] args) {
        int n = 6;
        System.out.println(toStr(calc(n)));


    }

    public static Set<Composition> calc(int n) {
        Set<Composition> possibility = new HashSet<Composition>();
        Composition composition = new Composition();
        switch (n) {
            case 1:
                composition.add(1);
                possibility.add(composition);
                return possibility;
            case 2:
                composition.add(1);
                composition.add(1);
                possibility.add(composition);
                return possibility;
            default:
                for (int i = 1; i <= n / 2; i++) {
                    composition = new Composition();
                    composition.add(i);
                    composition.add(n - i);
                    possibility.add(composition);
                    if (i <= n - i) {
                        Set<Composition> partial_pos = calc(n - i);
                        for (Composition pos : partial_pos) {
                            pos.add(i);
                            possibility.add(pos);
                        }
                    }
                }
                return possibility;
        }
    }

    public static String toStr(Set<Composition> possibility) {
        String str = "total : " + possibility.size() + "\n";
        for (Composition pos : possibility)
            str += toStr(pos);
        return str;
    }

    public static String toStr(Composition composition) {
        String str = composition.get(0) + "";
        for (int i = 1; i < composition.size(); i++)
            str += (" + " + composition.get(i));
        str += "\n";
        return str;
    }
}
