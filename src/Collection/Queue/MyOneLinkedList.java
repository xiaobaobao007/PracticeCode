package Collection.Queue;

/**
 * @author xiaobaobao
 * @date 2019/7/2 9:37
 */
public class MyOneLinkedList<T> {

	Node<T> node;
	int size = 0;

	public void add(T t) {
		size++;
		if (node == null) {
			node = new Node<>(t);
		} else {
			Node<T> queue = node;
			while (queue.last != null) {
				queue = queue.last;
			}
			queue.last = new Node<>(t);
		}
	}

	public void addIndex(int index, T t) {
		int i = 0;
		if (index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		Node<T> node = this.node;
		while (node != null) {//设置三处位置
			if (i++ == index) {
				Node<T> last = node.last;
				node.last = new Node<>(t);//前节点的后节点等于自己
				node.last.last = last;//自己的后节点等于之前前节点的后节点
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
		while (node != null) {
			System.out.print(node.me + "|");
			node = node.last;
		}
		System.out.println();
	}

	class Node<T> {
		T me;
		Node<T> last;

		public Node(T me) {
			this.me = me;
		}
	}
}
