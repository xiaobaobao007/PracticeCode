package com.intion.app.fight.buff;

import com.intion.app.fight.constant.FightConstant;
import com.intion.app.fight.data.AttackEvent;
import com.intion.app.fight.domain.Buff;
import com.intion.app.fight.domain.FightUnit;

/**
 * 持续击退的效果=恐惧被击退
 *
 * @author xiaobaobao
 * @date 2020/11/26，20:32
 */
public class RepelBuff extends Buff {
	@Override
	public AttackEvent effect(FightUnit atk, FightUnit def) {
		int x = def.positionX;
		int y = def.positionY;

		if (def.checkError(FightConstant.ONLY_MOVE_TYPE)) {
			return null;
		}

		int[][] xy = null;

		if (atk.positionX == def.positionX) {//在一列上
			if (def.positionY != 0 && def.positionY != FightConstant.AREA_HEIGHT - 1) {
				for (int i = 0; i < targetParams[1]; i++) {
					if (atk.positionY > def.positionY) {
						if (def.area.canMoveOnArea(x, y - 1)) {
							y--;
						} else {
							int length = def.positionY - y;
							xy = new int[length][2];
							for (; y < def.positionY; y++) {
								xy[--length][0] = def.positionX;
								xy[length][1] = y;
							}
							break;
						}
					} else {
						if (def.area.canMoveOnArea(x, y + 1)) {
							y++;
						} else {
							int length = y - def.positionY;
							xy = new int[length][2];
							for (; y > def.positionY; y--) {
								xy[--length][0] = def.positionX;
								xy[length][1] = y;
							}
							break;
						}
					}
				}
			}
		} else {
			if (def.positionX != 0 && def.positionX != FightConstant.AREA_WIDTH - 1) {
				for (int i = 0; i < targetParams[1]; i++) {
					if (atk.positionX > def.positionX) {
						if (def.area.canMoveOnArea(x - 1, y)) {
							x--;
						} else {
							int length = def.positionX - x;
							xy = new int[length][2];
							for (; x < def.positionX; x++) {
								xy[--length][0] = x;
								xy[length][1] = def.positionY;
							}
							break;
						}
					} else {
						if (def.area.canMoveOnArea(x + 1, y)) {
							x++;
						} else {
							int length = x - def.positionX;
							xy = new int[length][2];
							for (; x > def.positionX; x--) {
								xy[--length][0] = x;
								xy[length][1] = def.positionY;
							}
							break;
						}
					}
				}
			}
		}

		if (x == def.positionX && y == def.positionY) {
			return null;
		}

		if (xy != null) {
			def.area.move(def, xy);
		}
		return getAttackEvent(def, xy);
	}
}