package per.bmy.Collection.Set;

import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Vector;

public class HashSetTest {

    public static void main(String[] args) {
        test();
    }

    private static void test() {

        Vector<Integer> vector = new Vector<>();
        HashSet<Integer> set = new HashSet<>(vector);
        Vector<Integer> vector1 = new Vector<>(set);

    }

    public static void sout(NavigableSet<People> treeSet) {
        for (People p : treeSet) {
            System.out.printf("%4d", p.age);
        }
        System.out.println();
    }

    static class People implements Comparable<People> {
        private int age;

        People() {

        }

        People(int age) {
            this.age = age;
        }

        @Override
        public int compareTo(People o) {
            return age - o.age;
        }

        @Override
        public String toString() {
            return "People{" +
                    "age=" + age +
                    '}';
        }
    }

}
