package Collection.List;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
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

    public static void sout(LinkedList<Integer> linkedList){
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.printf("%4d",next);
        }
        System.out.println();
    }
}
