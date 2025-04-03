package per.bmy.AStar;

import java.util.HashMap;
import java.util.Map;

public class AStarNew {

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
    int splitX = 4;
    int splitY = (1 << splitX) - 1;

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int endXY;

    private Map<Integer, Integer> openMap = new HashMap<>();//边界所有值
    private Map<Integer, Integer> closeMap = new HashMap<>();//已经全部遍历过的

    public AStarNew(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.endXY = calculateXY(endX, endY);
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
            if (canReach(X, Y) && !openMap.containsKey(XY = calculateXY(X, Y)) && !closeMap.containsKey(XY)) {
                openMap.put(XY, calculateF(X, Y));
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

        int xy = calculateXY(startX, startY);
        closeMap.put(xy, calculateF(startX, startY));
        findNeighborNodes(startX, startY);
        if (openMap.containsKey(endXY)) {
            return true;
        }

        while (openMap.size() > 0) {
            // for (int i = 0; i < NODES.length; i++) {
            // 	for (int j = 0; j < NODES[0].length; j++) {
            // 		int iii = calculateXY(j, i);
            // 		if (iii == endXY) {
            // 			System.out.print("# ");
            // 		} else if (openMap.containsKey(iii)) {
            // 			System.out.print(". ");
            // 		} else if (closeMap.containsKey(iii)) {
            // 			System.out.print("  ");
            // 		} else {
            // 			System.out.print(NODES[i][j] + " ");
            // 		}
            // 	}
            // 	System.out.println();
            // }
            // System.out.println();

            Map.Entry<Integer, Integer> entry = findMinFNodeInOpneList();
            openMap.remove(entry.getKey());
            closeMap.put(entry.getKey(), entry.getValue());

            findNeighborNodes(entry.getKey() >> splitX, entry.getKey() & splitY);

            if (openMap.containsKey(endXY)) {
                return true;
            }
        }

        return false;
    }

    public int calculateF(int x, int y) {
        return Math.abs(x - startX) + Math.abs(y - startY) + Math.abs(x - endX) + Math.abs(y - endY);
    }

    public static void main(String[] args) {
        main();
    }

    public static void main() {
        new AStarNew(0, 0, NODES[0].length - 1, NODES.length - 1).findPath();
    }

    public int calculateXY(int x, int y) {
        return (x << splitX) + y;
    }

}