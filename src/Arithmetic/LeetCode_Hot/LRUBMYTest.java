package Arithmetic.LeetCode_Hot;

import java.util.HashMap;

import org.junit.Test;

/**
 * @author xiaobaobao
 * @date 2020/10/9，18:06
 */
public class LRUBMYTest {

	class LRUCache {

		class Node {
			public int key, val;
			public Node next, prev;

			public Node(int k, int v) {
				this.key = k;
				this.val = v;
			}
		}

		class Deque {

			private Node head, tail;
			private int size;

			public void addFirst(Node node) {
				if (head == null) {
					head = tail = node;
				} else {
					Node n = head;
					n.prev = node;
					node.next = n;
					head = node;
				}
				size++;
			}

			public void remove(Node node) {
				if (head == node && tail == node) {
					head = null;
					tail = null;
				} else if (tail == node) {
					node.prev.next = null;
					tail = node.prev;
				} else if (head == node) {
					node.next.prev = null;
					head = node.next;
				} else {
					node.prev.next = node.next;
					node.next.prev = node.prev;
				}
				size--;
			}

			public Node removeLast() {
				Node node = tail;
				remove(tail);
				return node;
			}

			public int size() {
				return size;
			}
		}

		private final HashMap<Integer, Node> map;
		private final Deque cache;
		private final int cap;

		public LRUCache(int capacity) {
			this.cap = capacity;
			map = new HashMap<>();
			cache = new Deque();
		}

		public int get(int key) {
			if (!map.containsKey(key)) return -1;
			int val = map.get(key).val;
			put(key, val);
			return val;
		}

		public void put(int key, int value) {
			Node x = new Node(key, value);

			if (map.containsKey(key)) {
				cache.remove(map.get(key));
				cache.addFirst(x);
				map.put(key, x);
			} else {
				if (cap == cache.size()) {
					Node last = cache.removeLast();
					map.remove(last.key);
				}
				cache.addFirst(x);
				map.put(key, x);
			}
		}
	}


	@Test
	public void main() {
		LRUCache cache = new LRUCache(2 /* 缓存容量 */);

		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));       // 返回  1
		cache.put(3, 3);    // 该操作会使得关键字 2 作废
		System.out.println(cache.get(2));       // 返回 -1 (未找到)
		cache.put(4, 4);    // 该操作会使得关键字 1 作废
		System.out.println(cache.get(1));       // 返回 -1 (未找到)
		System.out.println(cache.get(3));       // 返回  3
		System.out.println(cache.get(4));       // 返回  4
	}
}
