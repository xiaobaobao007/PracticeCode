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
		if (atk.positionX == def.positionX) {//在一列上
			if (def.positionY != 0 && def.positionY != FightConstant.AREA_HEIGHT - 1) {
				x = def.positionX - (atk.positionX - def.positionX);
				if (def.checkError(FightConstant.MOVE_TYPE, x, def.positionY)) {

				}
			}
		} else {

		}

		// def.area.move(def, );
		// return getAttackEvent(def);
		return null;
	}
}