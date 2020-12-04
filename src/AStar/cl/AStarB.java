package AStar.cl;

import java.util.HashMap;
import java.util.Map;

public class AStarB {

	public static final int[][] NODES = {
			{0, 1, 0, 0, 0, 1, 0, 0, 0},
			{0, 1, 0, 0, 0, 1, 0, 0, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 0, 0, 1, 0},
			{0, 0, 0, 1, 0, 0, 0, 1, 0},
	};

	int[][] xyOperation = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
	int splitX = 100;
	int splitY = 100;

	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private int endXY;

	private Map<Integer, Integer> openMap = new HashMap<>();//边界所有值
	private Map<Integer, Integer> closeMap = new HashMap<>();//已经全部遍历过的

	public AStarB(int startX, int startY, int endX, int endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.endXY = calcuteXY(endX, endY);
	}

	public Map.Entry<Integer, Integer> findMinFNodeInOpneList() {
		Map.Entry<Integer, Integer> min = null;
		for (Map.Entry<Integer, Integer> entry : openMap.entrySet()) {
			if (min == null) {
				min = entry;
				continue;
			}
			if (entry.getValue() < min.getValue()) {
				min = entry;
			}
		}
		return min;
	}

	public void findNeighborNodes(int x, int y) {
		int X;
		int Y;
		Integer XY;
		for (int[] ints : xyOperation) {
			X = x + ints[0];
			Y = y + ints[1];
			if (canReach(X, Y) && !openMap.containsKey(XY = calcuteXY(X, Y)) && !closeMap.containsKey(XY)) {
				openMap.put(XY, calcuteF(X, Y));
			}
		}
	}

	public boolean canReach(int x, int y) {
		if (y >= 0 && y < NODES.length && x >= 0 && x < NODES[0].length) {
			return NODES[y][x] == 0;
		}
		return false;
	}

	public boolean findPath() {

		int xy = calcuteXY(startX, startY);
		closeMap.put(xy, calcuteF(startX, startY));
		findNeighborNodes(startX, startY);
		if (openMap.containsKey(endXY)) {
			return true;
		}

		while (openMap.size() > 0) {
			Map.Entry<Integer, Integer> entry = findMinFNodeInOpneList();
			openMap.remove(entry.getKey());
			closeMap.put(entry.getKey(), entry.getValue());

			findNeighborNodes(entry.getKey() / splitX, entry.getKey() % splitY);

			if (openMap.containsKey(endXY)) {
				return true;
			}
		}

		return false;
	}

	public int calcuteF(int x, int y) {
		return Math.abs(x - startX) + Math.abs(y - startY) + Math.abs(x - endX) + Math.abs(y - endY);
	}

	public static void main(String[] args) {
		new AStarB(0, 0, NODES[0].length - 1, NODES.length - 1).main();
	}

	public void main() {
		findPath();
	}

	public int calcuteXY(int x, int y) {
		return (x * 100) + y;
	}

}