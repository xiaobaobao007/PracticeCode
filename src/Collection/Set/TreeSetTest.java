package Collection.Set;

import java.util.*;

public class TreeSetTest {

    static class People implements Comparable<People>{
        private int age;

        People() {

        }
        People(int age){
            this.age = age;
        }

        @Override
        public int compareTo(People o) {
            return age-o.age;
        }

        @Override
        public String toString() {
            return "People{" +
                    "age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        List<People> stringCollection = new ArrayList<>();
        stringCollection.add(new People(1));
        stringCollection.add(new People(51));
        stringCollection.add(new People(21));
        stringCollection.add(new People(12));
        TreeSet<People> treeSet1 = new TreeSet<>(stringCollection);
        sout(treeSet1);

        TreeSet<People> treeSet = new TreeSet<>((o1, o2) -> o2.age-o1.age);
        treeSet.add(new People(10));
        treeSet.add(new People(30));
        treeSet.add(new People(20));
        treeSet.add(new People(40));
        sout(treeSet);
        sout(treeSet.headSet(new People(25)));

//        System.out.println(treeSet.ceiling(new People(20)));
//        System.out.println(treeSet.ceiling(new People(25)));
//        System.out.println(treeSet.floor(new People(25)));
//        System.out.println(treeSet.higher(new People(25)));
//        System.out.println(treeSet.lower(new People(25)));

        treeSet = new TreeSet<>();
        treeSet.add(new People(10));
        treeSet.add(new People(30));
        treeSet.add(new People(20));
        treeSet.add(new People(40));
        sout(treeSet);
        sout(treeSet.headSet(new People(25)));

//        System.out.println(treeSet.ceiling(new People(20)));
//        System.out.println(treeSet.ceiling(new People(25)));
//        System.out.println(treeSet.floor(new People(25)));
//        System.out.println(treeSet.higher(new People(25)));
//        System.out.println(treeSet.lower(new People(25)));

    }

    public static void sout(NavigableSet<People> treeSet){
        for (People p : treeSet) {
            System.out.printf("%4d",p.age);
        }
        System.out.println();
    }

    private static void sout(TreeSet<People> treeSet){
        for (People p : treeSet) {
            System.out.printf("%4d",p.age);
        }
        System.out.println();
    }

    private static void sout(Set<People> treeSet){
        for (People p : treeSet) {
            System.out.printf("%4d",p.age);
        }
        System.out.println();
    }

//    public static void sout(NavigableSet<People> treeSet,int ...age){
//        for (int i : age) {
//            System.out.print(treeSet.ceiling(new People(i)));
//        }
//        System.out.println();
//    }
}
