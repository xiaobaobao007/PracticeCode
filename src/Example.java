/**
 * @author xiaobaobao
 * @date 2019/6/28 14:38
 */
public class Example extends Thread {
    public static void main(String args[]) throws InterruptedException {
        Thread example1 = new Example(1);
        Thread example2 = new Example(2);
        example1.start();
        example1.join();
        example2.start();
    }

    public Example(int id) {
        this.id = id;
    }

    private int id;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println(id);
        }
    }

}