package Collection.Tree;

/**
 * @author xiaobaobao
 * @date 2019/7/8 9:17
 */
public class Tree<T> {

	private Node<T> node;

	public void add(T t) {
		if (node == null) {
			node = new Node<>(t);
		} else {
			Node<T> tree = node;
			while (true) {
				if (t.hashCode() <= tree.data.hashCode()) {
					if (tree.left == null) {
						tree.left = new Node<>(t);
						break;
					} else {
						tree = tree.left;
					}
				} else {
					if (tree.right == null) {
						tree.right = new Node<>(t);
						break;
					} else {
						tree = tree.right;
					}
				}
			}
		}
	}

	public int getWidth() {
		int height = getHeight();
		int[] map = new int[height];
		getWidth(this.node, map, 0);
		int max = 0;
		for (int i = 0; i < height; i++) {
			if (max < map[i]) {
				max = map[i];
			}
		}
		return max;
	}

	public void getWidth(Node<T> node, int[] map, int height) {
		if (node != null) {
			map[height]++;
			getWidth(node.left, map, height + 1);
			getWidth(node.right, map, height + 1);
		}
	}

	public int getHeight() {
		return getHeight(this.node);
	}

	public int getHeight(Node<T> t) {
		if (t == null) {
			return 0;
		}
		return 1 + Math.max(getHeight(t.left), getHeight(t.right));
	}

	public void prveSout() {
		prveSout(this.node);
	}

	public void prveSout(Node<T> node) {
		if (node == null) {
			return;
		}
		prveSout(node.right);
		prveSout(node.left);
		System.out.print(node.data + "|");
	}

	public void sout() {
		int height = getHeight();
		int[] index = new int[height];
		Object[][] map = new Object[height][height];
		sout(this.node, map, index, 0);
		System.out.println("------------------------");
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < index[i]; j++) {
				System.out.printf("%-4s", (T) map[i][j]);
			}
			System.out.println();
		}
		System.out.printf("------------------------\n");
	}

	public void sout(Node<T> node, Object[][] map, int[] index, int height) {
		if (node == null) {
			return;
		}
		map[height][index[height]++] = node.data;
		sout(node.left, map, index, height + 1);
		sout(node.right, map, index, height + 1);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o instanceof Tree) {
			return equals(this.node, ((Tree) o).node);
		}
		return false;
	}

	public boolean equals(Node<T> a, Node<T> b) {
		if (!a.data.equals(b.data)) {
			return false;
		} else {
			return equals(a.left, b.left) && equals(a.right, b.right);
		}
	}

	class Node<T> {
		T data;
		Node<T> left;
		Node<T> right;

		public Node(T data) {
			this.data = data;
		}
	}
}
