package per.bmy.Collection.other;

import java.util.BitSet;

/**
 * @author xiaobaobao
 * @date 2020/10/8，15:49
 */
public class BitSetTest {
    public static void main(String args[]) {
        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);

        for (int i = 0; i < 16; i++) {
            if ((i % 2) == 0) bits1.set(i);
            if ((i % 3) == 0) bits2.set(i);
        }
        System.out.print("Initial pattern in bits1: ");
        System.out.println(bits1);
        System.out.print("Initial pattern in bits2: ");
        System.out.println(bits2);

        // AND bits
        bits2.and(bits1);
        System.out.print("bits2 AND bits1: ");
        System.out.println(bits2);

        // OR bits
        bits2.or(bits1);
        System.out.print("bits2 OR bits1: ");
        System.out.println(bits2);

        // XOR bits
        bits2.xor(bits1);
        System.out.print("bits2 XOR bits1: ");
        System.out.println(bits2);
    }
}
