package com.intion.app.fight.effect;

import com.intion.app.fight.constant.FightConstant;
import com.intion.app.fight.data.AttackEvent;
import com.intion.app.fight.domain.Buff;
import com.intion.app.fight.domain.FightUnit;

/**
 * 击退
 *
 * @author xiaobaobao
 * @date 2020/11/26，20:32
 */
public class RepelEffect extends Buff {
	@Override
	public AttackEvent effect(FightUnit atk, FightUnit def) {
		int x = def.positionX;
		int y = def.positionY;

		if (def.checkError(FightConstant.ONLY_MOVE_TYPE)) {
			return null;
		}

		if (atk.positionX == def.positionX) {//在一列上
			if (def.positionY != 0 && def.positionY != FightConstant.AREA_HEIGHT - 1) {
				for (int i = 0; i < targetParams[1]; i++) {
					if (atk.positionY > def.positionY) {
						if (def.area.canMoveOnArea(x, y - 1)) {
							y--;
						} else {
							break;
						}
					} else {
						if (def.area.canMoveOnArea(x, y + 1)) {
							y++;
						} else {
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
							break;
						}
					} else {
						if (def.area.canMoveOnArea(x + 1, y)) {
							x++;
						} else {
							break;
						}
					}
				}
			}
		}

		if (x == def.positionX && y == def.positionY) {
			return null;
		}

		def.area.move(def, x, y);
		return getAttackEvent(def, 1.0F);
	}
}