package com.intion.app.fight.buff;

import com.intion.app.fight.data.AttackEvent;
import com.intion.app.fight.domain.Buff;
import com.intion.app.fight.domain.FightUnit;

/**
 * 缠绕，禁足：无法移动
 *
 * @author xiaobaobao
 * @date 2020/12/2，20:17
 */
public class NotMoveBuff extends Buff {
	@Override
	public AttackEvent effect(FightUnit atk, FightUnit def) {
		afterBuffEffect();
		return null;
	}
}
