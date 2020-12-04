package com.intion.app.fight.buff;

import com.intion.app.fight.data.AttackEvent;
import com.intion.app.fight.domain.Buff;
import com.intion.app.fight.domain.FightUnit;

/**
 * 挂在敌人身上的毒，持续掉血等效果
 *
 * @author xiaobaobao
 * @date 2020/11/26，20:32
 */
public class SubHpBuff extends Buff {
	@Override
	public AttackEvent effect(FightUnit atk, FightUnit def) {
		float damage = atk.property.getMagAtk() - def.property.getMagDef();
		def.subHp(damage);

		return getAttackEvent(def, -damage);
	}
}