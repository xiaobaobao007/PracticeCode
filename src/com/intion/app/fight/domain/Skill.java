package com.intion.app.fight.domain;


import java.util.LinkedList;

import com.intion.app.fight.constant.FightConstant;
import com.intion.app.fight.data.BuffEvent;
import com.intion.app.fight.data.RoundEvent;

/**
 * 技能
 *
 * @author xiaobaobao
 * @date 2020/11/26，10:15
 */
public class Skill {

	private int skillId;
	private int distance;
	private boolean isNormalAttack;
	private LinkedList<Buff> buffList;

	public Skill(int distance) {
		isNormalAttack = true;
		this.distance = distance;
	}

	public Skill(int skillId, int distance) {
		this.skillId = skillId;
		this.distance = distance;
	}

	/**
	 * (√)先计算完一个技能的所有影响之后，再计算下一个技能
	 * (×)先计算一个作战单位下所有的技能的效果，再计算下一个单位
	 *
	 * @param counter 能否触发反击技能，反击之后不能再被反击
	 */
	public void doEffect(RoundEvent roundEvent, FightUnit atk, Area area, int defX, int defY, boolean counter) {
		if (buffList == null) {
			return;
		}
		for (Buff buff : buffList) {
			LinkedList<FightUnit> defUnitList = area.getTargetUnit(atk.isAttack, defX, defY, buff.targetType, buff.targetParams[0]);
			if (defUnitList != null && defUnitList.size() > 0) {
				for (FightUnit def : defUnitList) {
					buff.restart();
					roundEvent.addAttackEvents(buff.doEffect(atk, def));
					if (!(buff instanceof Effect) && !buff.canRemove()) {
						//此处克隆一个新对象
						def.addBuffEffect(buff.clone(atk));

						BuffEvent buffEvent = new BuffEvent();
						buffEvent.setStatus(FightConstant.BUFF_ADD);
						buffEvent.setBuffId(buff.buffType.buffId);
						buffEvent.setTargetId(def.heroId);
						roundEvent.addBuffEvents(buffEvent);
					}
					//触发反击效果等
					if (counter && def.defSkills != null) {

						RoundEvent passiveRound = new RoundEvent();
						passiveRound.setEventType(FightConstant.FIGHT_PLAYER_OPERATION_TYPE_ATTACK);
						passiveRound.setRound(roundEvent.getRound());
						passiveRound.setUserId(def.userId);
						passiveRound.setHeroId(def.heroId);
						passiveRound.setMoveToX(FightConstant.STAY_POSITION);
						passiveRound.setMoveToY(FightConstant.STAY_POSITION);
						passiveRound.setFightData(roundEvent.getFightData());
						roundEvent.getFightData().addLast(passiveRound);

						for (Skill defSkill : def.defSkills) {
							defSkill.doEffect(passiveRound, def, area, atk.positionX, atk.positionY, false);
						}

					}
				}
			}
		}
	}

	public void addBuffList(Buff buff) {
		if (buffList == null) {
			buffList = new LinkedList<>();
		}
		buffList.add(buff);
	}

	public int getSkillId() {
		return skillId;
	}

	public int getDistance() {
		return distance;
	}

	public boolean isNormalAttack() {
		return isNormalAttack;
	}
}
