package per.bmy.Collection.Queue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import org.junit.Test;

/**
 * @author xiaobaobao
 * @date 2020/10/15，18:56
 */
public class ArrayBlockingQueueTest {
    @Test
    public void one() {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        queue.add(1);
        queue.add(2);
        sout(queue);
        System.out.println(queue.poll());
        sout(queue);
    }

    public void sout(Queue queue) {
        if (queue.isEmpty()) {
            return;
        }
        System.out.print(queue.peek());
        boolean first = true;
        for (Object o : queue) {
            if (first) {
                first = false;
                continue;
            }
            System.out.print("," + o);
        }
        System.out.println();
    }
}
