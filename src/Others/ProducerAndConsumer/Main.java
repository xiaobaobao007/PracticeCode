package Others.ProducerAndConsumer;

public class Main {

    public final static int MAX_NUMS = 10;


    public static int C_Sleep = 100;
    public static int P_Sleep = 100;

    Breads breads;

    Main() {
        breads = new Breads();
        Producer producer = new Producer(1001, breads);
        new Thread(producer).start();
        new Consumer(1, breads).start();
        new Consumer(2, breads).start();
        new Consumer(3, breads).start();
        new Consumer(4, breads).start();
    }

    public static void main(String[] args) {
        new Main();
    }
}
