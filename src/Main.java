public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Father fs = new Son();
        System.out.println(fs.getClass());
        fs.sayHi();
        fs.ff();
        ((Son) fs).ss();

        System.out.println("---------------------------");

//        Son sf = (Son) new Father();
//        System.out.println(sf.getClass());
//        sf.sayHi();
//        sf.ff();
//        sf.ss();


    }

}

class Father {

    Father() {
    }

    public void ff() {
        System.out.println("Hi,aa!I'm Father.");
    }

    public void sayHi() {
        System.out.println("Hi,World!I'm Father.");
    }

}

class Son extends Father {

    public void ss() {
        System.out.println("Hello,World!I'm Son.");
    }

    @Override
    public void sayHi() {
        System.out.println("Hi,World!I'm Son.");
    }
}