package Collection.Queue;

import java.util.Iterator;
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
        linkedList.addLast(6);
        linkedList.addFirst(0);

        linkedList.removeFirst();
        linkedList.removeLast();

        sout(linkedList);

        System.out.println(linkedList.peek());

        linkedList.poll();
        sout(linkedList);

        sout(linkedList);
    }

    private static void sout(LinkedList<Integer> linkedList){
        for (Integer next : linkedList) {
            System.out.printf("%4d", next);
        }
        System.out.println();
    }

    private int test11(){
        return 1;
    }
}
