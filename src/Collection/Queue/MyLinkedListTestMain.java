package Collection.Queue;

/**
 * @author xiaobaobao
 * @date 2019/7/2 9:37
 */
public class MyLinkedListTestMain {
    public static void main(String[] args) {
        MyOneLinkedList<String> one = new MyOneLinkedList<>();
        one.add("1");
        one.add("2");
        one.add("3");
        one.add("4");
        one.add("5");
        one.addIndex(3,"10");
        one.print();

        MyTwoLinkedList<String> two = new MyTwoLinkedList<>();
        two.addLast("1");
        two.addLast("2");
        two.addLast("3");
        two.addHead("4");
        two.addHead("5");
        two.addIndex(2,"10");
        two.print();
    }
}
