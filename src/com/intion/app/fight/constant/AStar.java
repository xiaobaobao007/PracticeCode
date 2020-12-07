package com.intion.app.fight.constant;

import java.util.HashMap;
import java.util.Map;

import com.intion.app.fight.domain.FightUnit;

/**
 * 此算法在A*算法上进行了优化，在时间上优化一半左右,减少了申请大量的内存
 * 1：针对服务器只进行路径校验（即是否能够走到），不记录如何走。
 * 2：抛弃对象，采用二进制优化内存使用率
 */
@SuppressWarnings("DuplicatedCode")
public class AStar {
	private FightUnit[][] fightMap;

	private final static byte[][] xyOperation = {{0, -1, 0}, {0, 1, 1}, {1, 0, 2}, {-1, 0, 3}};//上，下，右，左
	private final static byte splitX = 3;
	private final static byte splitY = (1 << splitX) - 1;//最大值为7，实际值为4，所以足够用

	private final int startX;
	private final int startY;
	private final int endX;
	private final int endY;
	private final int endXY;
	private final int move;

	private Map<Integer, Integer> openMap = new HashMap<>();//边界所有值
	private Map<Integer, Integer> closeMap = new HashMap<>();//已经全部遍历过的

	private AStar(int startX, int startY, int endX, int endY, int move, FightUnit[][] fightMap) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.endXY = calculateXY(endX, endY);
		this.move = move;
		this.fightMap = fightMap;
	}

	private Map.Entry<Integer, Integer> findMinFNodeInOpenMap() {
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

	private void addNeighborNodes(int x, int y) {
		int X;
		int Y;
		Integer XY;
		for (byte[] incs : xyOperation) {
			X = x + incs[0];
			Y = y + incs[1];
			if (canMoveTo(X, Y) && !openMap.containsKey(XY = calculateXY(X, Y)) && !closeMap.containsKey(XY)) {
				openMap.put(XY, calculateF(X, Y));
			}
		}
	}

	private boolean canMoveTo(int x, int y) {
		return y >= 0 && y < FightConstant.AREA_HEIGHT && x >= 0 && x < FightConstant.AREA_WIDTH && fightMap[y][x] == null &&
					   FightConstant.calculateDistance(x, y, startX, startY) <= move;
	}

	private int calculateF(int x, int y) {
		return FightConstant.calculateDistance(x, y, startX, startY) + FightConstant.calculateDistance(x, y, endX, endY);
	}

	private int calculateXY(int x, int y) {
		return (x << splitX) + y;
	}

	private boolean findPath() {
		boolean result = false;
		int xy = calculateXY(startX, startY);
		closeMap.put(xy, calculateF(startX, startY));
		addNeighborNodes(startX, startY);
		if (openMap.containsKey(endXY)) {
			result = true;
		} else {
			while (openMap.size() > 0) {
				Map.Entry<Integer, Integer> entry = findMinFNodeInOpenMap();
				openMap.remove(entry.getKey());
				closeMap.put(entry.getKey(), entry.getValue());

				addNeighborNodes(entry.getKey() >> splitX, entry.getKey() & splitY);

				if (openMap.containsKey(endXY)) {
					result = true;
					break;
				}
			}
		}

		//help gc
		fightMap = null;
		openMap = null;
		closeMap = null;

		return result;
	}

	public static boolean calculate(int startX, int startY, int endX, int endY, int move, FightUnit[][] fightMap) {
		return new AStar(startX, startY, endX, endY, move, fightMap).findPath();
	}
}