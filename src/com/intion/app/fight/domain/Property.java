package com.intion.app.fight.domain;

import java.util.Map;

import com.intion.app.fight.constant.FightConstant;

import io.netty.util.internal.ThreadLocalRandom;

/**
 * 作战单元的属性值 {@link FightUnit#property}
 *
 * @author xiaobaobao
 * @date 2020/11/26，15:26
 */
public class Property {

	public float maxHp;
	public float phyAtk;
	public float magAtk;
	public float phyDef;
	public float magDef;
	public int critPer;//暴击率
	public int critDamPer;//爆伤率
	public int coattPer;//反击伤害普攻增加率

	public Map<Integer, Integer> buffPlies;//技能增加的层数,可为负数

	public float getRandomCritPer() {
		if (critPer == 0) {
			return 0;
		} else if (critPer >= FightConstant.TEN_THOUSAND) {
			return critDamPer;
		}
		if (critPer >= ThreadLocalRandom.current().nextInt(FightConstant.TEN_THOUSAND) + 1) {
			return critDamPer;
		}
		return 0;
	}

	public int getMyPlies(int buffId) {
		if (buffPlies == null) {
			return 0;
		}
		return buffPlies.getOrDefault(buffId, 0);
	}

	public void addProp(int type, float value) {
		// switch (type) {
		// 	case FightConstant.PROPRTTY_MAXHP:
		// 		maxHp += value;
		// 		break;
		// 	case FightConstant.PROPRTTY_PHYATK:
		// 		phyAtk += value;
		// 		break;
		// 	case FightConstant.PROPRTTY_MAGATK:
		// 		magAtk += value;
		// 		break;
		// 	case FightConstant.PROPRTTY_PHYDEF:
		// 		phyDef += value;
		// 		break;
		// 	case FightConstant.PROPRTTY_MAGDEF:
		// 		magDef += value;
		// 		break;
		// 	default:
		// 		throw new UnsupportedOperationException("float属性操作，类型未找到 " + type);
		// }
	}

	public void addProp(int type, int value) {
		// switch (type) {
		// 	case FightConstant.PROPRTTY_CRITPER:
		// 		critPer += value;
		// 		break;
		// 	case FightConstant.PROPRTTY_CRITDAMPER:
		// 		critDamPer += value;
		// 		break;
		// 	case FightConstant.PROPRTTY_COATTPER:
		// 		coattPer += value;
		// 		break;
		// 	default:
		// 		throw new UnsupportedOperationException("int属性操作，类型未找到 " + type);
		// }
	}

	public void setCritDamPer(int critDamPer) {
		this.critDamPer = (critDamPer + FightConstant.TEN_THOUSAND) / FightConstant.TEN_THOUSAND;
	}

	public float getPhyAtk() {
		return phyAtk;
	}

	public float getMagAtk() {
		return magAtk;
	}

	public float getPhyDef() {
		return phyDef;
	}

	public float getMagDef() {
		return magDef;
	}
}
