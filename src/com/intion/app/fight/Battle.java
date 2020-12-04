package com.intion.app.fight;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;

import com.intion.app.common.util.DateUtil;
import com.intion.app.fight.constant.BuffConstant;
import com.intion.app.fight.constant.FightConstant;
import com.intion.app.fight.data.BuffEvent;
import com.intion.app.fight.data.FightData;
import com.intion.app.fight.data.RoundEvent;
import com.intion.app.fight.domain.Area;
import com.intion.app.fight.domain.Buff;
import com.intion.app.fight.domain.FightUnit;
import com.intion.app.fight.domain.Skill;

/**
 * 一场战斗的内部管理
 *
 * @author xiaobaobao
 * @date 2020/11/26，16:26
 */
public class Battle {

	public final int startTime;
	public volatile int endTime;

	public Area area;
	public int round;
	public boolean isAtkRound = true;

	public final long atkUserId;
	public final long defUserId;

	// //地图中初始化的水潭，泥坑，毒雾等元素
	// LinkedList<MapBuff> originalBuff = new LinkedList<>();

	private final CyclicBarrier playerOperationCB;
	private final CyclicBarrier showFightCB;

	private final LinkedList<LinkedList<RoundEvent>> fightAllData = new LinkedList<>();

	public Battle(long atkUserId, long defUserId, Area area) {
		this.atkUserId = atkUserId;
		this.defUserId = defUserId;
		this.area = area;
		playerOperationCB = new CyclicBarrier(2);
		showFightCB = new CyclicBarrier(2);

		startTime = DateUtil.getNowCurc();
	}

	public void clearCB() throws Exception {
		while (playerOperationCB.getNumberWaiting() > 0) {
			playerOperationCB.await();
		}
		while (showFightCB.getNumberWaiting() > 0) {
			showFightCB.await();
		}
	}

	public FightData playerOperation(long userId, int operationType, int atkHeroId, int defHeroId, int x, int y, int skillId) {

		FightData fightData = new FightData();
		if (getTheRoundUserId() != userId) {
			return fightData.setResultAndReturn(-1);
		}

		try {
			if (operationType == FightConstant.FIGHT_PLAYER_OPERATION_TYPE_SHOW_OVER) {
				if (playerOperationCB.getNumberWaiting() != 0) {
					return fightData.setResultAndReturn(-2);
				}
				if (round >= FightConstant.MAX_ROUND) {//超出指定上限回合数
					System.out.println("战斗超出回合数：判定防守方胜利");
					fightData.setFightResult(FightConstant.FIGHT_RESULT_DEF_WIN);
					endTime = DateUtil.getNowCurc();
				}

				int fightResult = area.atkAndDefIsAllAlive();
				if (fightResult != 0) {
					System.out.println("战斗结束：" + fightResult);
					fightData.setFightResult(fightResult);
					endTime = DateUtil.getNowCurc();
				}

				showFightCB.await();
				isAtkRound = !isAtkRound;
				return fightData.setResultAndReturn(1);
			} else if (FightConstant.FIGHT_PLAYER_OPERATION_TYPE_NOTHING <= operationType && operationType <= FightConstant.FIGHT_PLAYER_OPERATION_TYPE_MOVE) {
				if (showFightCB.getNumberWaiting() != 0) {
					return fightData.setResultAndReturn(-3);
				}

				fightData.setRewardItemList(new LinkedList<>());
				fightAllData.add(fightData.getRoundEventList());

				int result = 1;
				switch (operationType) {
					case FightConstant.FIGHT_PLAYER_OPERATION_TYPE_NOTHING:
						doNothing();
						break;
					case FightConstant.FIGHT_PLAYER_OPERATION_TYPE_ATTACK:
						result = attack(atkHeroId, defHeroId, x, y, skillId);
						break;
					case FightConstant.FIGHT_PLAYER_OPERATION_TYPE_MOVE:
						result = move(atkHeroId, x, y);
						break;
				}
				if (result == 1) {
					beforeOrAfterRoundBuffEffect(isAtkRound, false);
					playerOperationCB.await();
				}
				fightData.setResult(result);
			}

		} catch (Exception e) {
			if (fightAllData.getLast().isEmpty()) {
				fightAllData.removeLast();
			}
			e.printStackTrace();
		}

		return fightData;
	}

	public void gameFightRoundStart() {
		while ((round = round + 1) <= FightConstant.MAX_ROUND) {
			System.out.println("waitting for 【" + (isAtkRound ? "atk" : "def") + "】 operation , round:" + round);
			try {
				playerOperationCB.await();
				// playerOperationCB.await(FightConstant.ROUND_DELAY, TimeUnit.SECONDS);
				// } catch (TimeoutException e) {
				// 	System.out.println("玩家操作已经超时");
				// 	doNothing();
			} catch (Exception e) {
				e.printStackTrace();
				playerOperationCB.reset();
			}

			try {
				showFightCB.await();
				// 	showFightCB.await(FightConstant.SHOW_FIGHT_DELAY, TimeUnit.SECONDS);
				// } catch (TimeoutException e) {
				// 	System.out.println("战斗播放已经超时");
				// 	doNothing();
			} catch (Exception e) {
				e.printStackTrace();
				showFightCB.reset();
			}
			System.out.println("战斗播放完成");

			if (endTime > 0) {
				break;
			}
		}
	}

	//回合开始前先计算相应的buff,正常情况下，作战单位下buff不会很多，所以用unit遍历需要检测的buff
	private void beforeOrAfterRoundBuffEffect(boolean isAtk, boolean isBeforeNotAfter) {
		RoundEvent roundEvent = null;
		for (FightUnit unit : area.fightUnitMap.values()) {
			if (unit.isAttack == isAtk || unit.buffEffectMapList == null) {
				continue;
			}
			Iterator<Map.Entry<Integer, LinkedList<Buff>>> mapIterator = unit.buffEffectMapList.entrySet().iterator();
			while (mapIterator.hasNext()) {
				Map.Entry<Integer, LinkedList<Buff>> listEntry = mapIterator.next();
				if (isBeforeNotAfter != BuffConstant.EFFECT_BEFORE_ROUND.contains(listEntry.getKey())) {
					continue;
				}
				Iterator<Buff> buffIterator = listEntry.getValue().iterator();
				while (buffIterator.hasNext()) {
					Buff buff = buffIterator.next();

					if (roundEvent == null) {
						roundEvent = new RoundEvent();
						roundEvent.setEventType(FightConstant.FIGHT_CALCULATE_BUFF);
						roundEvent.setRound(round);
					}
					roundEvent.addAttackEvents(buff.doEffect(unit));

					BuffEvent buffEvent = new BuffEvent();
					buffEvent.setBuffId(buff.buffType.buffId);
					buffEvent.setTargetId(unit.heroId);

					if (buff.canRemove()) {
						buff.remove(unit);
						buffIterator.remove();
						buffEvent.setStatus(FightConstant.BUFF_REMOVE);
					} else {
						buffEvent.setStatus(FightConstant.BUFF_EFFECT);
					}
				}
				if (listEntry.getValue().isEmpty()) {
					mapIterator.remove();
				}
			}
			if (unit.buffEffectMapList.isEmpty()) {
				unit.buffEffectMapList = null;
			}
		}
	}

	private void doNothing() {

		//回合前，先结算buff
		beforeOrAfterRoundBuffEffect(isAtkRound, true);
		//添加待命事件
		addNothingEvent();
	}

	private int attack(long atkHeroId, long defHeroId, int moveX, int moveY, int skillId) {

		FightUnit defUnit = area.fightUnitMap.get(defHeroId);
		if (defUnit == null) {
			return -1;
		} else if (isAtkRound == defUnit.isAttack || defUnit.checkError(FightConstant.BE_ATTACK_TYPE)) {
			return -2;
		}

		FightUnit atkUnit = area.fightUnitMap.get(atkHeroId);
		if (atkUnit == null) {
			return -3;
		} else if (atkUnit.isAttack == defUnit.isAttack) {
			return -7;
		}

		Skill skill = atkUnit.getSkillById(skillId);

		if (skill == null) {
			return -8;
		}
		if (skill.isNormalAttack()) {
			if (atkUnit.checkError(FightConstant.NORMAL_ATTACK_TYPE, skill, moveX, moveY, defUnit.positionX, defUnit.positionY)) {
				return -5;
			}
		} else if (atkUnit.checkError(FightConstant.SKILL_TYPE, skill, moveX, moveY, defUnit.positionX, defUnit.positionY)) {
			return -5;
		}

		//回合前，先结算buff
		beforeOrAfterRoundBuffEffect(isAtkRound, true);
		//先进行移动
		if (moveX >= 0 && moveY >= 0) {
			area.move(atkUnit, moveX, moveY);
		}
		//添加回合事件
		RoundEvent roundEvent = addAttackEvent(atkHeroId, skillId, moveX, moveY);
		//开始战斗,测试阶段，未有选择技能的选项，只执行第一个技能
		// atkUnit.getSkillById(skillId).doEffect(roundEvent, atkUnit, area, defUnit.positionX, defUnit.positionY, true);
		skill.doEffect(roundEvent, atkUnit, area, defUnit.positionX, defUnit.positionY, true);

		return 1;
	}

	private int move(long moveHeroId, int moveX, int moveY) {

		FightUnit moveUnit = area.fightUnitMap.get(moveHeroId);
		if (moveUnit == null) {
			return -1;
		} else if (moveUnit.checkError(FightConstant.OPERATION_TYPE, moveX, moveY)) {
			return -2;
		} else if (moveUnit.checkError(FightConstant.MOVE_TYPE, moveX, moveY)) {
			return -3;
		}

		//回合前，先结算buff
		beforeOrAfterRoundBuffEffect(isAtkRound, true);
		//移动
		area.move(moveUnit, moveX, moveY);
		//添加移动事件
		addMoveEvent(moveHeroId, moveX, moveY);

		return 1;
	}

	public boolean notCanBeStart() {
		return round != 0 || playerOperationCB.getNumberWaiting() != 0 || showFightCB.getNumberWaiting() != 0;
	}

	private void addNothingEvent() {
		RoundEvent roundEvent = new RoundEvent();
		roundEvent.setEventType(FightConstant.FIGHT_PLAYER_OPERATION_TYPE_NOTHING);
		roundEvent.setRound(round);
		roundEvent.setUserId(getTheRoundUserId());

		fightAllData.getLast().addLast(roundEvent);
	}

	private RoundEvent addAttackEvent(long heroId, int skillId, int x, int y) {
		RoundEvent roundEvent = new RoundEvent();
		roundEvent.setSkillId(skillId);
		roundEvent.setEventType(FightConstant.FIGHT_PLAYER_OPERATION_TYPE_ATTACK);
		return getRoundEvent(heroId, x, y, roundEvent);
	}

	private void addMoveEvent(long heroId, int x, int y) {
		RoundEvent roundEvent = new RoundEvent();
		roundEvent.setEventType(FightConstant.FIGHT_PLAYER_OPERATION_TYPE_MOVE);
		getRoundEvent(heroId, x, y, roundEvent);
	}

	private RoundEvent getRoundEvent(long heroId, int x, int y, RoundEvent roundEvent) {
		roundEvent.setRound(round);
		roundEvent.setUserId(getTheRoundUserId());
		roundEvent.setHeroId(heroId);
		roundEvent.setMoveToX(x);
		roundEvent.setMoveToY(y);

		roundEvent.setFightData(fightAllData.getLast());
		fightAllData.getLast().addLast(roundEvent);
		return roundEvent;
	}

	/**
	 * 得到此回合的战斗玩家id
	 */
	private long getTheRoundUserId() {
		if (isAtkRound) {
			return atkUserId;
		}
		return defUserId;
	}

	public boolean canRemoveThisBattle(int now) {
		if (endTime > 0 && now >= endTime) {
			endTime = -1;
			return true;
		}
		return false;
	}

}
