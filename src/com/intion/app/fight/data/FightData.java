package com.intion.app.fight.data;

import java.util.LinkedList;
import java.util.List;

import com.intion.app.xmlConfig.item.NomalItem;

/**
 * 返回的战斗数据结果
 *
 * @author xiaobaobao
 * @date 2020/11/30，19:11
 */
public class FightData {

	private List<NomalItem> rewardItemList;
	private final LinkedList<RoundEvent> roundEventList = new LinkedList<>();
	private int fightResult;//0未决胜出 1攻击方胜利，2防守方胜利

	private int result;//1数据正常，其余全是错误码

	public FightData() {
	}

	public FightData setResultAndReturn(int result) {
		this.result = result;
		return this;
	}

	public void addRoundEvent(RoundEvent event) {
		roundEventList.addLast(event);
	}

	public List<NomalItem> getRewardItemList() {
		return rewardItemList;
	}

	public void setRewardItemList(List<NomalItem> rewardItemList) {
		this.rewardItemList = rewardItemList;
	}

	public LinkedList<RoundEvent> getRoundEventList() {
		return roundEventList;
	}

	public int getFightResult() {
		return fightResult;
	}

	public void setFightResult(int fightResult) {
		this.fightResult = fightResult;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
}
