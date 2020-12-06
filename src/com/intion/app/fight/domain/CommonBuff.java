package com.intion.app.fight.domain;

import com.intion.app.fight.data.AttackEvent;

/**
 * 没有任何技能效果，只是根据unit是否包含buffId来执行相应的操作(禁止移动，不允许释放技能等)
 *
 * @author xiaobaobao
 * @date 2020/12/2，20:17
 */
public class CommonBuff extends Buff {
	@Override
	public AttackEvent effect(FightUnit atk, FightUnit def) {
		afterBuffEffect();
		return null;
	}
}
