package com.intion.app.fight.buff;

import com.intion.app.fight.data.AttackEvent;
import com.intion.app.fight.domain.Buff;
import com.intion.app.fight.domain.FightUnit;

/**
 * 加血
 *
 * @author xiaobaobao
 * @date 2020/11/26，20:32
 */
public class AddHpBuff extends Buff {
	@Override
	public AttackEvent effect(FightUnit atk, FightUnit def) {
		float addHp = atk.property.getMagAtk();
		def.addHp(addHp);

		return getAttackEvent(def, addHp);
	}
}