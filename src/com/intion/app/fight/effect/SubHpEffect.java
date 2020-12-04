package com.intion.app.fight.effect;

import com.intion.app.fight.data.AttackEvent;
import com.intion.app.fight.domain.Buff;
import com.intion.app.fight.domain.FightUnit;

/**
 * 1:普通攻击
 *
 * @author xiaobaobao
 * @date 2020/11/26，20:32
 */
public class SubHpEffect extends Buff {
	@Override
	public AttackEvent effect(FightUnit atk, FightUnit def) {
		float damage = atk.property.getPhyAtk() - def.property.getPhyDef();

		float critPer = atk.property.getRandomCritPer();
		if (critPer > 0) {
			damage *= critPer;
		}
		def.subHp(damage);

		return getAttackEvent(def, -damage, critPer > 0);
	}
}