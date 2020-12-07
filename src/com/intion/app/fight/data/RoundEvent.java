package com.intion.app.fight.data;

import java.util.LinkedList;

/**
 * @author xiaobaobao
 * @date 2020/11/30，19:54
 */
public class RoundEvent {

	private int eventType;//0buff结算(只有round、buffEvents和attackEvents),1待命，2攻击，3移动
	private int round;//第几回合的事件

	private long userId;//攻击方玩家id
	private long heroId;//攻击方英雄id

	private int skillId;//攻击方释放的技能id,0代表未做任何动作

	private int[][] xy;//移动距离，xy

	private LinkedList<BuffEvent> buffEvents;
	private LinkedList<AttackEvent> attackEvents;

	private transient LinkedList<RoundEvent> fightData;

	public RoundEvent() {
	}

	public void addBuffEvents(BuffEvent buffEvent) {
		if (buffEvents == null) {
			buffEvents = new LinkedList<>();
		}
		buffEvents.addLast(buffEvent);
	}

	public void addAttackEvents(AttackEvent attackEvent) {
		if (attackEvent == null) {
			return;
		}
		if (attackEvents == null) {
			attackEvents = new LinkedList<>();
		}
		attackEvents.addLast(attackEvent);
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getHeroId() {
		return heroId;
	}

	public void setHeroId(long heroId) {
		this.heroId = heroId;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public int[][] getXy() {
		return xy;
	}

	public void setXy(int[][] xy) {
		this.xy = xy;
	}

	public LinkedList<RoundEvent> getFightData() {
		return fightData;
	}

	public void setFightData(LinkedList<RoundEvent> fightData) {
		this.fightData = fightData;
	}
}
