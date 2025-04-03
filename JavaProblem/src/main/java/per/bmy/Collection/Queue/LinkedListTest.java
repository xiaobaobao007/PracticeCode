package per.bmy.Collection.Queue;

import java.util.LinkedList;

public class LinkedListTest {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        doAndSout(() -> linkedList.addFirst(0), linkedList);
        doAndSout(() -> linkedList.addLast(6), linkedList);
        doAndSout(() -> linkedList.removeFirst(), linkedList);
        doAndSout(() -> linkedList.removeLast(), linkedList);

        doAndSout(() -> linkedList.peek(), linkedList);
        doAndSout(() -> linkedList.poll(), linkedList);

    }

    private static void doAndSout(Runnable runnable, LinkedList<Integer> linkedList) {
        runnable.run();
        for (Integer next : linkedList) {
            System.out.printf("%-4d", next);
        }
        System.out.println();
    }

}
