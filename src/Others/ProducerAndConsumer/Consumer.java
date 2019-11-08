package Others.ProducerAndConsumer;

public class Consumer extends Thread {

	Breads p;
	private int id;

	public Consumer(int id, Breads p) {
		this.id = id;
		this.p = p;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(Main.C_Sleep);
				p.getLock().lock();
				if (p.getBread().size() > 0) {
					Integer first = p.getBread().getFirst();
					System.out.println(id + "吃了" + first);
					p.getBread().removeFirst();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				p.getLock().unlock();
			}
		}
	}
}