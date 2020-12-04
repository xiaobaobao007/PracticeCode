package com.intion.app.fight.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.intion.app.fight.constant.FightConstant;

/**
 * <pre>
 *
 * 		作战中的地图
 *
 *   	 0   1  2  3   4   5
 *   0	▢  ▢  ▢  ▢  ◼  ▢
 *   1	▢  ▢  ▢  ▢  ◼  ◼
 *   2	◼  ◼  ▢  ▢  ◼  ◼
 *   3	◼  ◼  ▢  ▢  ▢  ▢
 *   4	▢  ◼  ▢  ▢  ▢  ▢
 *
 * @author xiaobaobao
 * @date 2020/11/26，10:30
 * </pre>
 */
public class Area {

	public final FightUnit[][] areaMap = new FightUnit[FightConstant.AREA_HEIGHT][FightConstant.AREA_WIDTH];
	public Map<Long, FightUnit> fightUnitMap = new HashMap<>();// 还存活的单位 <heroId,unit>
	// public Map<Long, FightUnit> deadUnitMap = new HashMap<>();// 已死亡的单位 <heroId,unit>

	public void addFightUnit(FightUnit fightUnit) {
		if (!FightConstant.fightUnitCanInitPosition(fightUnit.isAttack, fightUnit.positionX, fightUnit.positionY)) {
			System.out.println("Area.addFightUnit");
			return;
		}
		if (areaMap[fightUnit.positionY][fightUnit.positionX] != null) {
			fightUnitMap.remove(areaMap[fightUnit.positionY][fightUnit.positionX].heroId);
		}
		areaMap[fightUnit.positionY][fightUnit.positionX] = fightUnit;
		fightUnitMap.put(fightUnit.heroId, fightUnit);
	}

	public boolean canMoveOnArea(int moveX, int moveY,int move) {
		return FightConstant.fightUnitCanMove(moveX, moveY) && areaMap[moveY][moveX] == null;
	}

	public LinkedList<FightUnit> getTargetUnit(boolean isAttack, int x, int y, TargetType targetType, int param) {
		LinkedList<FightUnit> linkedList = new LinkedList<>();
		switch (targetType) {
			case ONLY_THE_POSITION:
				linkedList.add(areaMap[y][x]);
				break;
			case CROSS_THE_POSITION:
				int i;
				FightUnit unit;
				linkedList.add(areaMap[y][x]);
				for (i = Math.min(FightConstant.AREA_WIDTH, x + param); i > x; i--) {//x+1->x
					if ((unit = areaMap[y][i]) != null && isAttack != unit.isAttack && unit.checkError(FightConstant.BE_ATTACK_TYPE)) {
						linkedList.add(unit);
					}
				}
				for (i = Math.max(0, x - param); i < x; i++) {//x-1->x
					if ((unit = areaMap[y][i]) != null && isAttack != unit.isAttack && unit.checkError(FightConstant.BE_ATTACK_TYPE)) {
						linkedList.add(unit);
					}
				}
				for (i = Math.min(FightConstant.AREA_HEIGHT, y + param); i > y; i--) {//y->y+1
					if ((unit = areaMap[i][x]) != null && isAttack != unit.isAttack && unit.checkError(FightConstant.BE_ATTACK_TYPE)) {
						linkedList.add(unit);
					}
				}
				for (i = Math.max(0, y - param); i < y; i++) {//y-1->y
					if ((unit = areaMap[i][x]) != null && isAttack != unit.isAttack && unit.checkError(FightConstant.BE_ATTACK_TYPE)) {
						linkedList.add(unit);
					}
				}
				break;
			case CIRCLE:
				for (int h = Math.max(0, y - param), n = param - y + h, add = 1; h < FightConstant.AREA_HEIGHT && n >= 0; h++, n += add) {
					for (int w = Math.max(0, x - n), m = x + n; w < FightConstant.AREA_WIDTH && w <= m; w++) {
						if ((unit = areaMap[h][w]) != null && isAttack != unit.isAttack && unit.checkError(FightConstant.BE_ATTACK_TYPE)) {
							linkedList.add(unit);
						}
					}
					if (n == param) {
						add = -1;
					}
				}
				break;
		}
		return linkedList;
	}

	/**
	 * @return 0全部存活，1攻击方胜利，2防守方胜利
	 */
	public int atkAndDefIsAllAlive() {
		boolean hasAtkHero = false;
		boolean hasDefHero = false;

		for (FightUnit unit : fightUnitMap.values()) {
			if (hasAtkHero && hasDefHero) {
				return 0;
			}
			if (unit.isAttack) {
				hasAtkHero = true;
			} else {
				hasDefHero = true;
			}
		}
		return hasDefHero ? FightConstant.FIGHT_RESULT_DEF_WIN : FightConstant.FIGHT_RESULT_ATK_WIN;
	}

	public void move(FightUnit moveUnit, int moveX, int moveY) {
		areaMap[moveUnit.positionY][moveUnit.positionX] = null;
		areaMap[moveY][moveX] = moveUnit;
		moveUnit.positionX = moveX;
		moveUnit.positionY = moveY;
	}


}
