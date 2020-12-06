package com.intion.app.fight;

import com.intion.app.fight.constant.BuffType;
import com.intion.app.fight.domain.*;
import com.intion.app.po.hero.UserHero;
import com.intion.app.service.AFightService;
import com.intion.app.xmlConfig.hero.HeroConfig;

/**
 * @author xiaobaobao
 * @date 2020/11/26，13:42
 */
public class FightTest {

	public static Property p = new Property();
	public static HeroConfig h = new HeroConfig();

	static {
		p.maxHp = 100.0F;
		p.phyAtk = 100.0F;
		p.magAtk = 100.0F;
		p.phyDef = 100.0F;
		p.magDef = 100.0F;
		p.critPer = 0;
		p.critDamPer = 0;
		p.coattPer = 0;

		h.setMove(1);
		h.setDistance(1);
	}

	static long id = 0L;

	// 正常阵位
	static int[][] atkUnit = {{1000, 0, 2}, {1001, 0, 3}, {1002, 1, 2}, {1003, 1, 3}, {1004, 1, 4}};
	static int[][] defUnit = {{1005, 4, 0}, {1006, 4, 1}, {1007, 4, 2}, {1008, 5, 1}, {1009, 5, 2}};

	// 防守方全部前移1格
	// static int[][] atkUnit = {{1000, 0, 2}, {1001, 0, 3}, {1002, 1, 2}, {1003, 1, 3}, {1004, 1, 4}};
	// static int[][] defUnit = {{1005, 3, 2}, {1006, 4, 2}, {1007, 3, 1}, {1008, 4, 1}, {1009, 3, 3}};

	// 测试技能效果，防守方包裹一个攻击方
	// static int[][] atkUnit = {{1000, 1, 1}};
	// static int[][] defUnit = {{1005, 2, 1}, {1006, 0, 1}, {1007, 1, 2}, {1008, 1, 0}};

	// 1V1测试
	// static int[][] atkUnit = {{1000, 2, 2}};
	// static int[][] defUnit = {{1005, 2, 3}};

	static long userIdA = 1L;
	static long userIdB = 2L;

	public static void main(String[] args) {
		AFightService a = new AFightService();
		Battle battle = new Battle(userIdA, userIdB, createArea());
		a.battle = battle;
		new Thread(battle::gameFightRoundStart).start();
		a.init();
	}

	public static Area createArea() {
		Area area = new Area();
		for (int[] unit : atkUnit) {
			area.addFightUnit(getOneUnit(userIdA, unit[0], true, unit[1], unit[2], area));
		}
		for (int[] unit : defUnit) {
			area.addFightUnit(getOneUnit(userIdB, unit[0], false, unit[1], unit[2], area));
		}
		return area;
	}

	public static FightUnit getOneUnit(long userId, int heroId, boolean isAttack, int positionX, int positionY, Area area) {
		UserHero userHero = new UserHero();
		userHero.setUid(userId);
		userHero.setHid(++id);

		FightUnit fightUnit = new FightUnit();
		fightUnit.init(isAttack, positionX, positionY, userHero, area);
		setSkill(fightUnit, 1001, BuffType.REPEL);
		return fightUnit;
	}

	//随机得到一个技能
	public static void setSkill(FightUnit fightUnit, int skillId, BuffType... buffType) {

		Skill skill = new Skill(skillId, 1);

		for (BuffType type : buffType) {
			Buff buff = type.cloneNew();
			buff.init(type, 1, fightUnit.isAttack, TargetType.ONLY_THE_POSITION, new int[]{3, 2});
			skill.addBuffList(buff);
		}

		fightUnit.atkSkills = new Skill[]{skill};
	}

}
