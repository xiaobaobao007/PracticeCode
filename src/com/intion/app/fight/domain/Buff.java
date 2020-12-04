package com.intion.app.fight.domain;

import com.intion.app.fight.buff.SubHpBuff;
import com.intion.app.fight.constant.BuffType;
import com.intion.app.fight.data.AttackEvent;

/**
 * buff
 *
 * @author xiaobaobao
 * @date 2020/11/26，9:48
 */
public abstract class Buff {

	public BuffType buffType;
	public int lastMaxRound;//生效的剩余轮数
	public int lastRound;//生效的剩余轮数
	public boolean effectTheAtk;//buff是否是对攻击方生效的

	public TargetType targetType;//目标选中类型
	public int[] targetParams;//[攻击目标参数,buff效果参数]

	public FightUnit atk;

	public void init(BuffType buffType, int lastRound, boolean effectTheAtk, TargetType targetType) {
		this.buffType = buffType;
		this.lastMaxRound = this.lastRound = lastRound;
		this.effectTheAtk = effectTheAtk;
		this.targetType = targetType;
	}

	public void init(BuffType buffType, int lastRound, boolean effectTheAtk, TargetType targetType, int[] targetParams) {
		this.buffType = buffType;
		this.lastMaxRound = this.lastRound = lastRound;
		this.effectTheAtk = effectTheAtk;
		this.targetType = targetType;
		this.targetParams = targetParams;
	}

	public AttackEvent doEffect(FightUnit def) {
		AttackEvent effect = effect(atk, def);
		afterBuffEffect();
		return effect;
	}

	public AttackEvent doEffect(FightUnit atk, FightUnit def) {
		AttackEvent effect = effect(atk, def);
		afterBuffEffect();
		return effect;
	}

	//因为对象被重用，所以生效前进行重置
	public void restart() {
		lastRound = lastMaxRound;
	}

	public abstract AttackEvent effect(FightUnit atk, FightUnit def);

	public void afterBuffEffect() {
		lastRound--;
	}

	public boolean canRemove() {
		return lastRound <= 0;
	}

	public AttackEvent remove(FightUnit def) {
		return null;
	}

	public AttackEvent getAttackEvent(FightUnit def, float damage) {
		return getAttackEvent(def, damage, false);
	}

	public AttackEvent getAttackEvent(FightUnit def, float damage, boolean crit) {
		AttackEvent attackEvent = new AttackEvent();
		attackEvent.setHeroId(def.heroId);
		attackEvent.setDead(!def.alive);
		attackEvent.setCrit(crit);
		attackEvent.setHp(def.hp);
		attackEvent.setSpSub(damage);
		return attackEvent;
	}

	public AttackEvent getAttackEvent(FightUnit def, int x, int y) {
		AttackEvent attackEvent = new AttackEvent();
		attackEvent.setHeroId(def.heroId);
		attackEvent.setDead(!def.alive);
		attackEvent.setMoveToX(x);
		attackEvent.setMoveToY(y);
		return attackEvent;
	}

	public Buff cloneNew() {
		return clone(atk);
	}

	public Buff clone(FightUnit atk) {
		Buff buff = new SubHpBuff();
		buff.lastRound = this.lastRound;
		buff.effectTheAtk = this.effectTheAtk;
		buff.targetType = this.targetType;
		buff.targetParams = this.targetParams;
		buff.atk = atk;
		return buff;
	}

}
