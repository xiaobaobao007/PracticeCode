import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
//        Father fs = new Son();
//        System.out.println(fs.getClass());
//        fs.sayHi();
//        fs.ff();
//        ((Son) fs).ss();
//
//        System.out.println("---------------------------");
//
//        Son sf = (Son) new Father();
//        System.out.println(sf.getClass());
//        sf.sayHi();
//        sf.ff();
//        sf.ss();
//        long l = System.currentTimeMillis();
//        System.out.println(l);
//        l = l & 0xFFFFFFFFL;
//        System.out.println(l);
//        l = l << 32;
//        System.out.println(l);
//        l = l | 1 & Integer.MAX_VALUE;
//        System.out.println(l);
//        System.out.println(((System.currentTimeMillis() & 0xFFFFFFFFL) << 32 | 1 & Integer.MAX_VALUE));
        String[] s = {"A1", "C1", "B1", "D178", "A0", "C0", "B0", "D0", "A2", "C2", "B2", "D2"};
        int[] a = new int[1];
        Arrays.sort(s, ((o1, o2) -> ((a[0] = (o1.charAt(0) - o2.charAt(0)))) == 0 ? Integer.valueOf(o1.substring(1)) - Integer.valueOf(o2.substring(1)) : a[0]));
        for (String q : s) {
            System.out.println(q);
        }
    }

}

class Father {

    Father() {
    }

    void ff() {
        System.out.println("Hi,aa!I'm Father.");
    }

    public void sayHi() {
        System.out.println("Hi,World!I'm Father.");
    }

}

class Son extends Father {

    void ss() {
        System.out.println("Hello,World!I'm Son.");
    }

    @Override
    public void sayHi() {
        System.out.println("Hi,World!I'm Son.");
    }
}