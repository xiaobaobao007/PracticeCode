package com.intion.app.fight.domain;

import com.intion.app.fight.constant.BuffType;

/**
 * 效果
 *
 * @author xiaobaobao
 * @date 2020/12/2，17:04
 */
public abstract class Effect extends Buff {

	public void init(BuffType buffType, boolean effectTheAtk, TargetType targetType) {
		this.buffType = buffType;
		this.effectTheAtk = effectTheAtk;
		this.targetType = targetType;
	}

}
