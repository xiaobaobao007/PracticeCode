package com.intion.app.fight.buff;

import com.intion.app.fight.data.AttackEvent;
import com.intion.app.fight.domain.FightUnit;
import com.intion.app.fight.effect.RepelEffect;

/**
 * 持续击退的效果=恐惧被击退
 *
 * @author xiaobaobao
 * @date 2020/11/26，20:32
 */
public class RepelBuff extends RepelEffect {
	@Override
	public AttackEvent effect(FightUnit atk, FightUnit def) {
		//先执行after无所谓，不影响。代码写起来更简洁
		afterBuffEffect();
		return super.effect(atk, def);
	}
}