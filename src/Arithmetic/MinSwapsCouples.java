package Arithmetic;

/**
 * @author xiaobaobao
 * @date 2020/8/27，17:26
 *
 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
 *
 * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
 *
 * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
 *
 */
public class MinSwapsCouples {

	public static void main(String[] args) {
		// int length = 3;
		// int[] array = new int[length *= 2];
		// Random random = new Random();
		// for (int i = 0; i < length; i++) {
		// 	array[i] = i;
		// }
		// for (int i = 0; i < length; i++) {
		// 	int a = random.nextInt(length);
		// 	int b;
		// 	while ((b = random.nextInt(length)) != a) {
		// 		int c = array[a];
		// 		array[a] = array[b];
		// 		array[b] = c;
		// 		break;
		// 	}
		// }
		// System.out.println(Arrays.toString(array));

		int[] array = new int[]{2, 3, 5, 0, 1, 4};
		System.out.println(new MinSwapsCouples().minSwapsCouples(array));
	}

	int[] parent, size;

	int minSwapsCouples(int[] row) {

		int N = row.length / 2;
		parent = new int[N];
		size = new int[N];

		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < row.length; i += 2) {
			union(row[i] / 2, row[i + 1] / 2);
		}

		int circle = 0;
		for (int i = 0; i < N; i++) {
			if (i != findRoot(i)) {
				circle++;
			}
		}

		return circle;
	}

	int findRoot(int x) {
		if (x != parent[x]) {
			parent[x] = findRoot(parent[x]);
		}
		return parent[x];
	}

	void union(int x, int y) {
		x = findRoot(x);
		y = findRoot(y);
		if (x != y) {
			if (size[x] < size[y]) {
				parent[x] = y;
				size[y] += size[x];
			} else {
				parent[y] = x;
				size[x] += size[y];
			}
		}
	}

}
