package com.intion.app.fight.constant;

import com.intion.app.fight.buff.*;
import com.intion.app.fight.domain.*;
import com.intion.app.fight.effect.*;

/**
 * @author xiaobaobao
 * @date 2020/12/3，9:47
 */
public enum BuffType {

	//效果技能
	ATTACK(10001, new SubHpEffect()),//普通攻击
	REPEL(10002, new RepelEffect()),//击退
	TRANSPOSITION(10003, new ________________()),//换位
	RESURGENCE(10004, new ________________()),//复活
	SUMMON(10005, new ________________()),//召唤
	PURIFICATION(10006, new ________________()),//净化
	//不能进行主动操作
	BE_STONE(20001, 1, new ________________()),//石化：无法进行任何操作
	VERTIGO(20002, 1, new ________________()),//眩晕：无法进行任何操作
	FROZEN(20003, 1, new ________________()),//冰冻：无法进行任何操作
	WINDING(20004, 1, new ________________()),//缠绕：无法进行任何操作
	BE_SARCASM(20008, 1, new ________________()),//被嘲讽：不受控制，自动攻击某个目标
	FEAR(20020, 1, new ________________()),//恐惧：不受控制，自动远离目标
	//回合前需要判定的
	BLEEDING(20011, 1, new SubHpBuff()),//流血：每回合受伤害
	POISONING(20012, 1, new SubHpBuff()),//中毒：每回合受伤害
	ADD_BLOOD(20012, 1, new ________________()),//加血：每回合加血
	//其他单一效果
	SILENCE(20007, 1, new ________________()),//沉默：无法放主动技能
	GROUNDED(20005, 1, new NotMoveBuff()),//禁足：无法移动
	SEAL(20006, 1, new ________________()),//封印：无法普通攻击
	DAZE(20009, 1, new ________________()),//发呆：无法反击
	SLOW_DOWN(20010, 1, new ________________()),//减速：降低移动x格
	CHAOS(20013, 1, new ________________()),//混乱：对敌我都有伤害
	ANELECTRICSHOCK(20014, 1, new ________________()),//电击：释放技能会受到伤害
	LEG(20015, 1, new ________________()),//断腿：移动会受到伤害
	THEINJURY(20016, 1, new ________________()),//反伤：攻击者会受到伤害
	INVINCIBLE(20017, 1, new ________________()),//无敌：不受伤害和控制
	CONFUSED(20018, 1, new ________________()),//迷惑：敌人变为我方单位
	STEALTH(20019, 1, new ________________()),//隐身：不能被作为目标
	LINK(20021, 1, new ________________());//链接：被连接的单位，会受到相同的效果

	public final int buffId;//buffId
	public final boolean onlyEffectOneRound;//是否只生效一轮，即为效果不是buff
	public final int defaultMaxPlies;//默认的层数
	public final Buff baseBuff;//用于被克隆

	BuffType(int buffId, Buff baseBuff) {
		this.buffId = buffId;
		this.onlyEffectOneRound = true;
		this.defaultMaxPlies = 0;
		this.baseBuff = baseBuff;
	}

	BuffType(int buffId, int defaultMaxPlies, Buff baseBuff) {
		this.buffId = buffId;
		this.onlyEffectOneRound = false;
		this.defaultMaxPlies = defaultMaxPlies;
		this.baseBuff = baseBuff;
	}

	public Buff cloneNew() {
		return baseBuff.cloneNew();
	}

}