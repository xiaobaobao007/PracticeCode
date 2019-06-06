package Collection.Set;

import java.util.*;

public class TreeSetTest {

    static class people{
        private int age;
        people(int age){
            this.age = age;
        }

    }

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        TreeSet<people> treeSet = new TreeSet<>((o1, o2) -> o2.age-o1.age);
        treeSet.add(new people(1));
        treeSet.add(new people(3));
        treeSet.add(new people(2));
        treeSet.add(new people(4));

        sout(treeSet);

    }

    public static void sout(TreeSet<people> treeSet){
        for (people p : treeSet) {
            System.out.printf("%4d",p.age);
        }
        System.out.println();
    }
}
