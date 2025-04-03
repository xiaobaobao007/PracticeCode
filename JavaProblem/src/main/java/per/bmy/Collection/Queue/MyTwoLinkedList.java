package per.bmy.Collection.Queue;

/**
 * @author xiaobaobao
 * @date 2019/7/2 10:34
 */
public class MyTwoLinkedList<T> {

    Node<T> node;
    int size = 0;

    public void addHead(T t) {
        size++;
        if (node == null) {
            node = new Node<>(t);
        } else {
            Node<T> queue = node;
            while (queue.prve != null) {
                queue = queue.prve;
            }
            queue.prve = new Node<>(t);
            queue.prve.last = queue;
        }
    }

    public void addLast(T t) {
        size++;
        if (node == null) {
            node = new Node<>(t);
        } else {
            Node<T> queue = node;
            while (queue.last != null) {
                queue = queue.last;
            }
            queue.last = new Node<>(t);
            queue.last.prve = queue;
        }
    }

    public void addIndex(int index, T t) {
        int i = 0;
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        Node<T> node = this.node;
        while (node.prve != null) {
            node = node.prve;
        }
        while (node != null) {//设置五处位置
            if (i++ == index) {
                Node<T> last = node.last;
                Node<T> tNode = new Node<>(t);//自己
                node.last = tNode;//自己的前节点的后节点
                tNode.prve = node;//自己的前节点
                tNode.last = last;//自己的后节点
                tNode.last.prve = tNode;//自己的后节点的前节点
                break;
            }
            node = node.last;
        }
    }

    public Node<T> getNode() {
        return node;
    }

    public void print() {
        Node<T> node = this.node;
        while (node.prve != null) {
            node = node.prve;
        }
        while (node != null) {
            System.out.print(node.me + "|");
            node = node.last;
        }
        System.out.println();
    }

    class Node<T> {
        T me;
        Node<T> prve;
        Node<T> last;

        public Node(T me) {
            this.me = me;
        }
    }
}
