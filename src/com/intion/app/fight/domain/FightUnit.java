package com.intion.app.fight.domain;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.intion.app.fight.FightTest;
import com.intion.app.fight.constant.BuffConstant;
import com.intion.app.fight.constant.BuffType;
import com.intion.app.fight.constant.FightConstant;
import com.intion.app.po.hero.UserHero;
import com.intion.app.xmlConfig.hero.HeroConfig;

/**
 * 战斗单元
 *
 * @author xiaobaobao
 * @date 2020/11/26，10:05
 */
public class FightUnit {

	public long userId;
	public long heroId;

	public boolean isAttack;
	public int positionX;
	public int positionY;

	public HeroConfig heroConfig = FightTest.h;

	public float hp;
	public Property property = FightTest.p;
	public boolean alive = true;

	public Area area;
	public Skill[] atkSkills;//主动技能等，0一定是普攻

	public Map<Integer, LinkedList<Buff>> buffEffectMapList;

	public void init(boolean isAttack, int positionX, int positionY, UserHero userHero, Area area) {

		userId = userHero.getUid();
		heroId = userHero.getHid();

		this.isAttack = isAttack;
		this.positionX = positionX;
		this.positionY = positionY;

		property = new Property();

		hp = property.maxHp = 100;
		property.phyAtk = 10;
		property.magAtk = 0;
		property.phyDef = 0;

		// hp = property.maxHp = userHero.getHp();
		// property.phyAtk = userHero.getPatt();
		// property.magAtk = userHero.getMatt();
		// property.phyDef = userHero.getPdef();

		property.magDef = userHero.getMdef();
		property.critPer = userHero.getCritper();
		property.critDamPer = userHero.getCritdamper();
		property.coattPer = userHero.getCoattper();

		this.area = area;
	}

	public void addBuffEffect(Buff buff) {
		if (buffEffectMapList == null) {
			//确保插入顺序执行buff效果，不能按照id的hash槽进行计算
			buffEffectMapList = new LinkedHashMap<>(8, 1.0F, false);
		}
		int myMaxPlies = buff.buffType.defaultMaxPlies + property.getMyPlies(buff.buffType.buffId);
		LinkedList<Buff> buffList = buffEffectMapList.computeIfAbsent(buff.buffType.buffId, (k) -> new LinkedList<>());
		if (buffList.size() < myMaxPlies) {
			buffList.add(buff);
		}
	}

	public boolean checkError(int operationType) {
		return checkError(operationType, null, null, 0, 0);
	}

	public boolean checkError(int operationType, int[][] xy) {
		return checkError(operationType, null, xy, 0, 0);
	}

	public boolean checkError(int operationType, Skill skill, int[][] xy, int fightX, int fightY) {
		switch (operationType) {
			case FightConstant.OPERATION_TYPE:
				return !alive || myBuffsHas(BuffConstant.NOT_OPERATION_BUFF_SET);
			case FightConstant.SKILL_TYPE:
				if (xy != null) {
					return moveError(xy) || FightConstant.calculateDistance(xy, fightX, fightY) > skill.getDistance();
				}
				return !alive || myBuffsHas(BuffType.SILENCE) || FightConstant.calculateDistance(positionX, positionY, fightX, fightY) > skill.getDistance();
			case FightConstant.MOVE_TYPE:
				if (moveError(xy)) {
					return true;
				}
			case FightConstant.ONLY_MOVE_TYPE:
				return !alive || myBuffsHas(BuffType.GROUNDED);
			case FightConstant.NORMAL_ATTACK_TYPE:
				if (xy != null) {
					return moveError(xy) || FightConstant.calculateDistance(xy, fightX, fightY) > skill.getDistance();
				}
				return !alive || myBuffsHas(BuffType.SEAL) || FightConstant.calculateDistance(positionX, positionY, fightX, fightY) > skill.getDistance();
			case FightConstant.BE_ATTACK_TYPE:
				return !alive || myBuffsHas(BuffType.STEALTH);
			case FightConstant.BE_ATTACK_RETURN_TYPE:
				return !alive || myBuffsHas(BuffType.DAZE) || !myBuffsHas(BuffType.THEINJURY);
			default:
				return true;
		}
	}

	public Skill getSkillById(int skillId) {
		if (atkSkills != null) {
			for (Skill skill : atkSkills) {
				if (skill.getSkillId() == skillId) {
					return skill;
				}
			}
		}
		return null;
	}

	/**
	 * 能否进行移动,判断地图位置和移动距离限制, 当移动记录大于1，进行A*算法计算
	 */
	private boolean moveError(int[][] xy) {
		int move = getMove();

		for (int[] ints : xy) {
			if (!area.canMoveOnArea(ints[0], ints[1]) || FightConstant.calculateDistance(positionX, positionY, ints[0], ints[1]) > move) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 计算减低移动距离
	 */
	private int getMove() {
		if (buffEffectMapList != null) {
			LinkedList<Buff> buffList = buffEffectMapList.get(BuffType.SLOW_DOWN.buffId);
			if (buffList != null && buffList.size() > 0) {
				int max = 0;
				for (Buff buff : buffList) {
					max = Math.max(max, buff.targetParams[1]);
				}
				return Math.max(0, heroConfig.getMove() - max);
			}
		}
		return heroConfig.getMove();
	}

	private boolean myBuffsHas(BuffType buffType) {
		if (buffEffectMapList == null) {
			return false;
		}
		return buffEffectMapList.containsKey(buffType.buffId);
	}

	private boolean myBuffsHas(Set<Integer> set) {
		if (buffEffectMapList == null) {
			return false;
		}
		for (Integer buffId : buffEffectMapList.keySet()) {
			if (set.contains(buffId)) {
				return true;
			}
		}
		return false;
	}

	public void addHp(float hp) {
		if (!alive) {
			return;
		}
		this.hp = Math.min(this.hp + hp, property.maxHp);
	}

	public void subHp(float hp) {
		this.hp -= hp;
		if (this.hp <= 0) {
			this.hp = 0;
			alive = false;

			area.areaMap[positionY][positionX] = null;
			area.fightUnitMap.remove(userId);
		}
	}

}
