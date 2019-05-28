package Others.ProducerAndConsumer;

public class Producer implements Runnable {

    Breads p;
    private int id;
    private int num = 0;

    public Producer(int id, Breads p) {
        this.id = id;
        this.p = p;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(Main.P_Sleep);
                p.getLock().lock();
                if (p.getBread().size() < Main.MAX_NUMS) {
                    System.out.println(id + "生产了" + ++num);
                    p.getBread().addLast(num);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                p.getLock().unlock();
            }
        }
    }
}